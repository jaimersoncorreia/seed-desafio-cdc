package tech.bacuri.livro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SequenceGenerator(sequenceName = "SQ_PEDIDO", allocationSize = 1, name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido")
    private Integer id;

    @JsonIgnore
    @NotNull
    @Valid
    @OneToOne
    private Compra compra;

    @Size(min = 1)
    @ElementCollection
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido(Compra compra, Set<ItemPedido> itens) {
        Assert.isTrue(!itens.isEmpty(), "Todo pedido deve ter pelo menos um item");
        this.compra = compra;
        this.itens.addAll(itens);
    }

    public boolean totalIgual(@Positive @NotNull BigDecimal total) {
        BigDecimal bigDecimal = itens.stream()
                .map(ItemPedido::total)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_EVEN);

        return bigDecimal.compareTo(total) == 0;

    }

    public BigDecimal getTotal() {
        return itens.stream().map(ItemPedido::getTotal).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
