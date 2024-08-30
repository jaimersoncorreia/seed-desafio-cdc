package tech.bacuri.livro.controller.dto.pedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class NovoPedidoForm {

    @Positive
    @NotNull
    private BigDecimal total;

    @Size
    @Valid
    private List<NovoPedidoItemForm> itens = new ArrayList<>();
}
