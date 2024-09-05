package tech.bacuri.livro.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalhesForm {
    private BigDecimal valorOriginal;
    private BigDecimal valorCupomAplicado;
    private Boolean cupomAplicado;

    public DetalhesForm(BigDecimal valorOriginal, BigDecimal valorCupomAplicado, Boolean cupomAplicado) {
        this.valorOriginal = valorOriginal;
        this.valorCupomAplicado = valorCupomAplicado;
        this.cupomAplicado = cupomAplicado;
    }
}
