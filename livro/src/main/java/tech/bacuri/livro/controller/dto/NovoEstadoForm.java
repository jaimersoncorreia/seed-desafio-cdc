package tech.bacuri.livro.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tech.bacuri.livro.annotation.ExistsId;
import tech.bacuri.livro.annotation.ValorUnico;
import tech.bacuri.livro.entity.Estado;
import tech.bacuri.livro.entity.Pais;

@Data
public class NovoEstadoForm {
    @NotBlank
    @ValorUnico(domainClass = Estado.class, fieldName = "nome")
    private String nome;

    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id")
    private Integer idPais;

    public Estado toEntity() {
        return Estado.builder()
                .nome(getNome())
                .pais(Pais.builder().id(getIdPais()).build())
                .build();
    }
}
