package tech.bacuri.livro.controller.dto.compra;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import tech.bacuri.livro.annotation.CpfOrCnpj;
import tech.bacuri.livro.annotation.ExistsId;
import tech.bacuri.livro.controller.dto.pedido.NovoPedidoForm;
import tech.bacuri.livro.entity.Compra;
import tech.bacuri.livro.entity.Estado;
import tech.bacuri.livro.entity.Pais;

import java.util.Objects;

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

    //    @NotNull
//    @ExistsId(domainClass = Estado.class, fieldName = "id")
    private Integer idEstado; //(caso aquele pais tenha estado)

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @Valid
    @NotNull
    private NovoPedidoForm pedido;

    public Compra toModel() {
        var compra = Compra.builder()
                .email(email)
                .nome(nome)
                .sobrenome(sobrenome)
                .documento(documento)
                .endereco(endereco)
                .complemento(complemento)
                .cep(cep)
                .cidade(cidade)
                .pais(Pais.builder().id(idPais).build());

        if (!Objects.isNull(idEstado))
            compra.estado(Estado.builder().id(idEstado).build());

        return compra.build();
    }

    public boolean temEstado() {
        return !Objects.isNull(idEstado);
    }

}
