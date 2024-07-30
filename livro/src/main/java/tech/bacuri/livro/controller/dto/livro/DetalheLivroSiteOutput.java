package tech.bacuri.livro.controller.dto.livro;

import lombok.Data;
import tech.bacuri.livro.controller.dto.autor.DetalheAutorSiteOutput;
import tech.bacuri.livro.entity.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DetalheLivroSiteOutput {
    private String titulo;

    private DetalheAutorSiteOutput autor;

    private BigDecimal preco;

    private String resumo;

    private String sumario;

    private Integer numeroPagina;

    private String isbn;

    private LocalDate dataPublicacao;

    public DetalheLivroSiteOutput(Livro livro) {
        this.titulo = livro.getTitulo();
        this.autor = new DetalheAutorSiteOutput(livro.getAutor());
        this.preco = livro.getPreco();
        this.resumo = livro.getResumo();
        this.numeroPagina = livro.getNumeroPagina();
        this.isbn = livro.getIsbn();
        this.sumario = livro.getSumario();
        this.dataPublicacao = livro.getDataPublicacao();
    }
}
