package tech.bacuri.livro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@SequenceGenerator(sequenceName = "SQ_PAIS", allocationSize = 1, name = "pais")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais")
    private Integer id;

    @NotBlank
    private String nome;
}
