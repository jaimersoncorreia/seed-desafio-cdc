package tech.bacuri.livro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.bacuri.livro.controller.dto.categoria.NovaCategoriaForm;
import tech.bacuri.livro.entity.Categoria;
import tech.bacuri.livro.repository.CategoriaRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    @Transactional
    @PostMapping
    public Categoria save(@Valid @RequestBody NovaCategoriaForm form) {
        return categoriaRepository.save(form.toEntity());
    }
}
