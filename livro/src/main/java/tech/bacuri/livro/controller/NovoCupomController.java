package tech.bacuri.livro.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.bacuri.livro.controller.dto.NovoCupomForm;
import tech.bacuri.livro.entity.Cupom;

@RequiredArgsConstructor
@RestController
public class NovoCupomController {

    @PersistenceContext
    private final EntityManager manager;

    @PostMapping("/cupons")
    @Transactional
    public ResponseEntity<?> criar(@Valid @RequestBody NovoCupomForm form) {
        Cupom novoCupom = form.toModel();
        manager.persist(novoCupom);
        return ResponseEntity.ok(novoCupom);
    }

}
