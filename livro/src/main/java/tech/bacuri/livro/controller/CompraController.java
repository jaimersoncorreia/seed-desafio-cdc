package tech.bacuri.livro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tech.bacuri.livro.controller.dto.compra.NovaCompraForm;
import tech.bacuri.livro.entity.Compra;
import tech.bacuri.livro.validator.EstadoPertencePaisValidator;

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/compras")
public class CompraController {

    private final EstadoPertencePaisValidator estadoPertencePaisValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(estadoPertencePaisValidator);
    }

    @PostMapping
    public ResponseEntity<?> novaCompra(@Valid @RequestBody NovaCompraForm form) {
        Compra novaCompra = form.toModel();
        return ok(novaCompra);
    }
}
