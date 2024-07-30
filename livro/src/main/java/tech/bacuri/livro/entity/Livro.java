package tech.bacuri.livro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@SequenceGenerator(sequenceName = "SQ_LIVRO", allocationSize = 1, name = "livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "livro")
    private Integer id;

    @NotBlank
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
    private String isbn;

    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataPublicacao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @ManyToOne
    private Autor autor;
}
