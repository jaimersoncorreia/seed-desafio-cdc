package tech.bacuri.livro.controller.dto.autor;

import lombok.Data;
import tech.bacuri.livro.entity.Autor;

@Data
public class DetalheAutorSiteOutput {
    private String nome;
    private String descricao;

    public DetalheAutorSiteOutput(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }
}
