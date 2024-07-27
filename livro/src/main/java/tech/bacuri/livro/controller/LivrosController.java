package tech.bacuri.livro.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.bacuri.livro.entity.Livro;
import tech.bacuri.livro.repository.LivroRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/livros")
public class LivrosController {

    private final LivroRepository livroRepository;

    @PostMapping
    public Livro save(@Valid @RequestBody NovoLivroForm form) {
        return livroRepository.save(form.toEntity());
    }
}
