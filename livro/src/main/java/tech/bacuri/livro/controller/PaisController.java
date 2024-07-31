package tech.bacuri.livro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.bacuri.livro.controller.dto.pais.NovoPaisForm;
import tech.bacuri.livro.entity.Pais;
import tech.bacuri.livro.repository.PaisRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/paises")
public class PaisController {
    private final PaisRepository paisRepository;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody NovoPaisForm form) {
        Pais pais = form.toEntity();
        return ResponseEntity.ok(paisRepository.save(pais));
    }
}
