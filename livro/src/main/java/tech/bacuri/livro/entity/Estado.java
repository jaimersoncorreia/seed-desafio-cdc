package tech.bacuri.livro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(sequenceName = "SQ_ESTADO", allocationSize = 1, name = "estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado")
    private Integer id;

    @NotBlank
    private String nome;

    @NotNull
    @ManyToOne
    private Pais pais;

    public boolean pertenceA(Pais pais) {
        return Objects.equals(pais, this.pais);
    }

    public boolean naoPertenceA(Pais pais) {
        return !pertenceA(pais);
    }
}
