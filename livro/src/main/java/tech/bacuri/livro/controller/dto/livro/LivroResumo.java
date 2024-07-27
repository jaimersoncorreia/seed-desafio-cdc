package tech.bacuri.livro.controller.dto.livro;

import lombok.Builder;
import lombok.Data;
import tech.bacuri.livro.entity.Livro;

import java.util.List;

@Data
@Builder
public class LivroResumo {
    private Integer id;
    private String titulo;

    public static LivroResumo toResumo(Livro livro) {
        return LivroResumo.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .build();
    }

    public static List<LivroResumo> toResumoList(List<Livro> livroList) {
        return livroList.stream().map(LivroResumo::toResumo).toList();
    }
}
