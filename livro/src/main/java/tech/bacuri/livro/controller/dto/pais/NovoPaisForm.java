package tech.bacuri.livro.controller.dto.pais;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import tech.bacuri.livro.annotation.ValorUnico;
import tech.bacuri.livro.entity.Pais;

@Data
public class NovoPaisForm {
    @NotBlank
    @ValorUnico(domainClass = Pais.class, fieldName = "nome")
    private String nome;

    public Pais toEntity() {
        return Pais.builder().nome(this.nome).build();
    }
}
