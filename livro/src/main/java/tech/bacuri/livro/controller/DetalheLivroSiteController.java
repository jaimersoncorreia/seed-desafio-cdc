package tech.bacuri.livro.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.bacuri.livro.controller.dto.livro.DetalheLivroSiteOutput;
import tech.bacuri.livro.entity.Livro;
import tech.bacuri.livro.repository.LivroRepository;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produdos/{id}")
public class DetalheLivroSiteController {
    private final LivroRepository livroRepository;

    @GetMapping
    public ResponseEntity<?> site(@PathVariable Integer id) {
        Optional<Livro> optionalLivro = livroRepository.findById(id);

        if (optionalLivro.isEmpty())
            return ResponseEntity.notFound().build();

        DetalheLivroSiteOutput detalheLivroSiteOutput = new DetalheLivroSiteOutput(optionalLivro.get());

        return ResponseEntity.ok(detalheLivroSiteOutput);
    }
}
