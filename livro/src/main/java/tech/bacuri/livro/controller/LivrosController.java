package tech.bacuri.livro.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bacuri.livro.controller.dto.livro.LivroResumo;
import tech.bacuri.livro.controller.dto.livro.NovoLivroForm;
import tech.bacuri.livro.entity.Livro;
import tech.bacuri.livro.repository.LivroRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/livros")
public class LivrosController {

    private final LivroRepository livroRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livro save(@Valid @RequestBody NovoLivroForm form) {
        return livroRepository.save(form.toEntity());
    }

    @GetMapping
    public List<LivroResumo> list() {
        return LivroResumo.toResumoList((List<Livro>) livroRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> pagina(@PathVariable Integer id) {
        Optional<Livro> livroOptional = livroRepository.findById(id);
        if (livroOptional.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(livroOptional.get());
    }
}
