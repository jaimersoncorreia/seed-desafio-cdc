package tech.bacuri.livro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tech.bacuri.livro.controller.dto.categoria.NovaCategoriaForm;
import tech.bacuri.livro.entity.Categoria;
import tech.bacuri.livro.repository.CategoriaRepository;
import tech.bacuri.livro.validator.ProibeNomeDuplicadoCategoriaValidator;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;
    private final ProibeNomeDuplicadoCategoriaValidator proibeNomeDuplicadoCategoriaValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(proibeNomeDuplicadoCategoriaValidator);
    }

    @Transactional
    @PostMapping
    public Categoria save(@Valid @RequestBody NovaCategoriaForm form) {
        return categoriaRepository.save(form.toEntity());
    }
}
