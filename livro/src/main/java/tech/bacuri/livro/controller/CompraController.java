package tech.bacuri.livro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tech.bacuri.livro.controller.dto.compra.NovaCompraForm;
import tech.bacuri.livro.entity.Compra;
import tech.bacuri.livro.repository.CompraRepository;
import tech.bacuri.livro.repository.CupomRepository;
import tech.bacuri.livro.repository.LivroRepository;
import tech.bacuri.livro.validator.CupomValidoValidator;
import tech.bacuri.livro.validator.EstadoPertencePaisValidator;

@RequiredArgsConstructor
@RestController
@RequestMapping("/compras")
public class CompraController {

    private final EstadoPertencePaisValidator estadoPertencePaisValidator;
    private final CupomValidoValidator cupomValidoValidator;
    private final LivroRepository livroRepository;
    private final CompraRepository compraRepository;
    private final CupomRepository cupomRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(estadoPertencePaisValidator);
        binder.addValidators(cupomValidoValidator);
    }

    @PostMapping
    public Compra novaCompra(@Valid @RequestBody NovaCompraForm form) {
        Compra novaCompra = form.toModel(livroRepository, cupomRepository);
        return compraRepository.save(novaCompra);
    }
}
