package tech.bacuri.livro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.bacuri.livro.controller.dto.autor.AutorForm;
import tech.bacuri.livro.entity.Autor;
import tech.bacuri.livro.repository.AutorRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorRepository autorRepository;

    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autor save(@Valid @RequestBody AutorForm autorForm) {
        return autorRepository.save(autorForm.toAutorResponse());
    }
}