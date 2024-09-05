package tech.bacuri.livro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tech.bacuri.livro.controller.dto.DetalhesForm;
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
        var novaCompra = form.toModel(livroRepository, cupomRepository);
        return compraRepository.save(novaCompra);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> detalhes(@PathVariable Integer id) {

        var compraOptional = compraRepository.findById(id);
        if (compraOptional.isEmpty())
            throw new RuntimeException("Detalhes da compra não encontrado!");

        var compra = compraOptional.get();

        var cupomAplicado = compra.getTotalCupomAplicado();
        var total = compra.getTotal();
        var aplicado = !compra.naoAplicado();

        var form = new DetalhesForm(total, cupomAplicado, aplicado);
        return ResponseEntity.ok(form);
    }
}
