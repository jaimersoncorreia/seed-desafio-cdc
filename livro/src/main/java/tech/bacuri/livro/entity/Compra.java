package tech.bacuri.livro.entity;

import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import tech.bacuri.livro.annotation.CpfOrCnpj;
import tech.bacuri.livro.annotation.ExistsId;
import tech.bacuri.livro.controller.dto.pedido.NovoPedidoForm;

@Builder
@Data
public class Compra {
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
    @ManyToOne
    private Pais pais;

    @NotNull
    @ExistsId(domainClass = Estado.class, fieldName = "id")
    @ManyToOne
    private Estado estado; //(caso aquele pais tenha estado)

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @Valid
    @NotNull
    private NovoPedidoForm pedido;
}
