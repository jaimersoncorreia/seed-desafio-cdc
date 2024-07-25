package tech.bacuri.livro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(sequenceName = "SQ_AUTOR", allocationSize = 1, name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor")
    private Integer id;

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 400)
    private String descricao;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dtRegistro;

    @PrePersist
    public void prePersist() {
        this.dtRegistro = LocalDateTime.now();
    }
}
