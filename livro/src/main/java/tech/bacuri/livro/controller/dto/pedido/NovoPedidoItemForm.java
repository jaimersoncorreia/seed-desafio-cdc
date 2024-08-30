package tech.bacuri.livro.controller.dto.pedido;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import tech.bacuri.livro.annotation.ExistsId;
import tech.bacuri.livro.entity.Livro;

@Data
public class NovoPedidoItemForm {

    @NotNull
    @ExistsId(domainClass = Livro.class, fieldName = "id")
    private Integer idLivro;

    @Positive
    private Integer quantidade;
}
