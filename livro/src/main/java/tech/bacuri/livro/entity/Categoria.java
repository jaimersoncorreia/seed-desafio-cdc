package tech.bacuri.livro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(sequenceName = "SQ_CATEGORIA", allocationSize = 1, name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria")
    private Integer id;

    @NotBlank
    @Size(max = 400)
    private String nome;
}
