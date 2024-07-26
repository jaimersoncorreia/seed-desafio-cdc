package tech.bacuri.livro.controller.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tech.bacuri.livro.entity.Categoria;

@Data
public class NovaCategoriaForm {
    @NotBlank
    @Size(max = 400)
    private String nome;

    public Categoria toEntity() {
        return Categoria.builder().nome(getNome()).build();
    }
}
