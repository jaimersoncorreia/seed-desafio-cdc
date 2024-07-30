package tech.bacuri.livro.controller.dto.livro;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tech.bacuri.livro.annotation.ExistsId;
import tech.bacuri.livro.annotation.ValorUnico;
import tech.bacuri.livro.entity.Autor;
import tech.bacuri.livro.entity.Categoria;
import tech.bacuri.livro.entity.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class NovoLivroForm {
    @NotBlank
    @ValorUnico(domainClass = Livro.class, fieldName = "titulo")
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    private String sumario;

    @NotNull
    @DecimalMin(value = "20.00")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Integer numeroPagina;

    @NotBlank
    @ValorUnico(domainClass = Livro.class, fieldName = "isbn")
    private String isbn;

    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataPublicacao;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Integer idCategoria;

    @NotNull
    @ExistsId(domainClass = Autor.class, fieldName = "id")
    private Integer idAutor;


    public Livro toEntity() {
        return Livro.builder()
                .titulo(getTitulo())
                .resumo(getResumo())
                .sumario(getSumario())
                .preco(getPreco())
                .numeroPagina(getNumeroPagina())
                .isbn(getIsbn())
                .dataPublicacao(getDataPublicacao())
                .categoria(Categoria.builder().id(getIdCategoria()).build())
                .autor(Autor.builder().id(getIdAutor()).build())
                .build();
    }
}
