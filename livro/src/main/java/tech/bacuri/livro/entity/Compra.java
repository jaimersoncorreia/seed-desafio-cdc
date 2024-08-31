package tech.bacuri.livro.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.bacuri.livro.annotation.CpfOrCnpj;

import java.util.function.Function;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@SequenceGenerator(sequenceName = "SQ_COMPRA", allocationSize = 1, name = "compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra")
    private Integer id;

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
    @ManyToOne
    private Pais pais;

    @ManyToOne
    private Estado estado; //(caso aquele pais tenha estado)

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;


    @Valid
    @NotNull
    @OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
    private Pedido pedido;

    public void processarPedido(Function<Compra, Pedido> funcaoCriaPedido) {
        this.pedido = funcaoCriaPedido.apply(this);
    }

}
