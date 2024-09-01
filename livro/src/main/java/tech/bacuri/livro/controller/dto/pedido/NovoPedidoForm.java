package tech.bacuri.livro.controller.dto.pedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.util.Assert;
import tech.bacuri.livro.entity.Compra;
import tech.bacuri.livro.entity.ItemPedido;
import tech.bacuri.livro.entity.Pedido;
import tech.bacuri.livro.repository.LivroRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class NovoPedidoForm {

    @Positive
    @NotNull
    private BigDecimal total;

    @Size
    @Valid
    private List<NovoPedidoItemForm> itens = new ArrayList<>();

    public Function<Compra, Pedido> toModel(LivroRepository livroRepository) {
        Set<ItemPedido> itensCalculados = this.itens.stream()
                .map(item -> item.toModel(livroRepository))
                .collect(Collectors.toSet());

        return (compra) -> {
            Pedido pedido = new Pedido(compra, itensCalculados);
            Assert.isTrue(pedido.totalIgual(total.setScale(2, RoundingMode.HALF_EVEN)), "Olha, o total enviado não corresponde ao enviado");
            return pedido;
        };
    }
}
