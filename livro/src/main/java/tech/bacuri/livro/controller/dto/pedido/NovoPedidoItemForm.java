package tech.bacuri.livro.controller.dto.pedido;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import tech.bacuri.livro.annotation.ExistsId;
import tech.bacuri.livro.entity.ItemPedido;
import tech.bacuri.livro.entity.Livro;
import tech.bacuri.livro.repository.LivroRepository;

import java.math.RoundingMode;

@Data
public class NovoPedidoItemForm {

    @NotNull
    @ExistsId(domainClass = Livro.class, fieldName = "id")
    private Integer idLivro;

    @Positive
    private Integer quantidade;

    public ItemPedido toModel(LivroRepository livroRepository) {
        Livro livro = livroRepository.findById(idLivro).orElseThrow();
        return ItemPedido.builder()
                .livro(livro)
                .quantidade(quantidade)
                .precoMomento(livro.getPreco().setScale(2, RoundingMode.HALF_EVEN))
                .build();
    }
}
