package tech.bacuri.livro.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tech.bacuri.livro.controller.dto.compra.NovaCompraForm;
import tech.bacuri.livro.entity.Estado;
import tech.bacuri.livro.entity.Pais;
import tech.bacuri.livro.repository.EstadoRepository;
import tech.bacuri.livro.repository.PaisRepository;

@RequiredArgsConstructor
@Component
public class EstadoPertencePaisValidator implements Validator {
    private final PaisRepository paisRepository;
    private final EstadoRepository estadoRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCompraForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors())
            return;

        NovaCompraForm form = (NovaCompraForm) target;

        Pais pais = paisRepository.findById(form.getIdPais()).orElse(null);
        Estado estado = estadoRepository.findById(form.getIdEstado()).orElse(null);

        Assert.notNull(estado, "Não deveria ter vindo nulo");

        if (estado.naoPertenceA(pais)) {
            errors.rejectValue("idEstado", null, "Esse estado não pertence a esse país");
        }

    }
}
