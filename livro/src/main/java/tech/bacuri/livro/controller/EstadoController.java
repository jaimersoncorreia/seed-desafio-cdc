package tech.bacuri.livro.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.bacuri.livro.controller.dto.NovoEstadoForm;
import tech.bacuri.livro.entity.Estado;
import tech.bacuri.livro.repository.EstadoRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estados")
public class EstadoController {
    private final EstadoRepository estadoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody NovoEstadoForm form) {
        Estado estado = form.toEntity();
        return ResponseEntity.ok(estadoRepository.save(estado));
    }
}
