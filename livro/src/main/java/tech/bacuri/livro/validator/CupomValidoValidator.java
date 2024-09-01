package tech.bacuri.livro.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tech.bacuri.livro.controller.dto.compra.NovaCompraForm;
import tech.bacuri.livro.entity.Cupom;
import tech.bacuri.livro.repository.CupomRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CupomValidoValidator implements Validator {

    private final CupomRepository cupomRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCompraForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors())
            return;

        NovaCompraForm form = (NovaCompraForm) target;
        Optional<String> possivelCodigo = form.obterCodigoCupom();
        if (possivelCodigo.isPresent()) {
            Cupom cupom = cupomRepository.getCupomByCodigo(possivelCodigo.get());
            if (cupom.naoEhvalido()) {
                errors.rejectValue("codigoCupom", null, "Este cupom não é mais válido");
            }
        }
    }
}
