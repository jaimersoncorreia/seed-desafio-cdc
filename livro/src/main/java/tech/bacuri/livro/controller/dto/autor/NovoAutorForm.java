package tech.bacuri.livro.controller.dto.autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tech.bacuri.livro.entity.Autor;

@Data
public class NovoAutorForm {
    @NotBlank
    private String nome;

    @Email
    private String email;

    @NotBlank
    @Size(max = 400)
    private String descricao;

    public Autor toAutorResponse() {
        return Autor.builder()
                .nome(nome)
                .email(email)
                .descricao(descricao)
                .build();
    }
}
