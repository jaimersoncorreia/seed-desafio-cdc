package tech.bacuri.livro.controller.dto.compra;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.StringUtils;
import tech.bacuri.livro.annotation.CpfOrCnpj;
import tech.bacuri.livro.annotation.ExistsId;
import tech.bacuri.livro.controller.dto.pedido.NovoPedidoForm;
import tech.bacuri.livro.entity.Compra;
import tech.bacuri.livro.entity.Cupom;
import tech.bacuri.livro.entity.Estado;
import tech.bacuri.livro.entity.Pais;
import tech.bacuri.livro.repository.CupomRepository;
import tech.bacuri.livro.repository.LivroRepository;

import java.util.Objects;
import java.util.Optional;

@Data
@Builder
public class NovaCompraForm {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @CpfOrCnpj
    private String documento;//(cpf/cnpj)

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotNull
    private String cidade;

    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id")
    private Integer idPais;

    @ExistsId(domainClass = Estado.class, fieldName = "id")
    private Integer idEstado; //(caso aquele pais tenha estado)

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @Valid
    @NotNull
    private NovoPedidoForm pedido;

    @ExistsId(domainClass = Cupom.class, fieldName = "codigo")
    private String codigoCupom;

    public Compra toModel(LivroRepository livroRepository, CupomRepository cupomRepository) {
        var pais = Pais.builder().id(idPais).build();

        var funcaoCriaPedido = pedido.toModel(livroRepository);

        var compraBuilder = Compra.builder()
                .email(email)
                .nome(nome)
                .sobrenome(sobrenome)
                .documento(documento)
                .endereco(endereco)
                .complemento(complemento)
                .cep(cep)
                .cidade(cidade)
                .telefone(telefone)
                .pais(pais);

        if (!Objects.isNull(idEstado))
            compraBuilder.estado(Estado.builder().id(idEstado).build());

        var compra = compraBuilder.build();
        compra.processarPedido(funcaoCriaPedido);

        if (StringUtils.hasText(codigoCupom)) {
            var cupom = cupomRepository.getCupomByCodigo(codigoCupom);
            compra.aplicarCupom(cupom);
        }

        return compra;
    }

    public boolean temEstado() {
        return !Objects.isNull(idEstado);
    }

    public Optional<String> obterCodigoCupom() {
        return Optional.ofNullable(codigoCupom);
    }
}
