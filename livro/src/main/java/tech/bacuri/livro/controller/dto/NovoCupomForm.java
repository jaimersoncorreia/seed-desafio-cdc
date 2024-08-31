package tech.bacuri.livro.controller.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tech.bacuri.livro.annotation.ValorUnico;
import tech.bacuri.livro.entity.Cupom;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class NovoCupomForm {
    @NotBlank
    @ValorUnico(domainClass = Cupom.class, fieldName = "codigo")
    private String codigo;

    @Positive
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal percentualDesconto;

    @Future
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate validade;

    public Cupom toModel() {
        return new Cupom(codigo, percentualDesconto, validade);
    }
}
