package tech.bacuri.livro.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class ItemPedido {

    @ManyToOne
    private Livro livro;

    @Positive
    private Integer quantidade;

    @Positive
    private BigDecimal precoMomento;

    public BigDecimal total() {
        return precoMomento.multiply(BigDecimal.valueOf(quantidade)).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getTotal() {
        return total();
    }
}
