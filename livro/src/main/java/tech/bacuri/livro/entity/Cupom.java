package tech.bacuri.livro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
public class Cupom {
    @NotBlank
    @Id
    private String codigo;

    @Positive
    @NotNull
    private BigDecimal percentualDesconto;

    @Future
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate validade;

    public Cupom(@NotBlank @NotBlank String codigo,
                 @Positive @NotNull @Positive @NotNull BigDecimal percentualDesconto,
                 @Future @NotNull @Future @NotNull LocalDate validade) {

        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.validade = validade;
    }
}
