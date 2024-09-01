package tech.bacuri.livro.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Embeddable
public class CupomAplicado {
    @ManyToOne
    private Cupom cupom;

    @NotNull
    @Positive
    private BigDecimal percentualDescontoMomento;

    @NotNull
    @Future
    private LocalDate validadeMomento;

    public CupomAplicado(Cupom cupom) {
        this.cupom = cupom;
        this.percentualDescontoMomento = cupom.getPercentualDesconto();
        this.validadeMomento = cupom.getValidade();
    }
}
