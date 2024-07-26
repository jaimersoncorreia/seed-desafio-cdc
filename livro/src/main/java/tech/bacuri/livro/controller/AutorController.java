package tech.bacuri.livro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tech.bacuri.livro.controller.dto.autor.NovoAutorForm;
import tech.bacuri.livro.validator.ProibeEmailDuplicadoAutorValidator;
import tech.bacuri.livro.entity.Autor;
import tech.bacuri.livro.repository.AutorRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorRepository autorRepository;
    private final ProibeEmailDuplicadoAutorValidator proibeEmailDuplicadoAutorValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        //1
        binder.addValidators(proibeEmailDuplicadoAutorValidator);
    }

    //1
    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autor save(@Valid @RequestBody NovoAutorForm form) {
        //1
        return autorRepository.save(form.toAutorResponse());
    }
}