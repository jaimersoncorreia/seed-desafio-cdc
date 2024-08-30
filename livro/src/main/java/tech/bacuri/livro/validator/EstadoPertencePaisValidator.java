package tech.bacuri.livro.validator;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tech.bacuri.livro.controller.dto.compra.NovaCompraForm;
import tech.bacuri.livro.entity.Estado;
import tech.bacuri.livro.entity.Pais;
import tech.bacuri.livro.repository.EstadoRepository;
import tech.bacuri.livro.repository.PaisRepository;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class EstadoPertencePaisValidator implements Validator {
    private final PaisRepository paisRepository;
    private final EstadoRepository estadoRepository;

    @Override
    public boolean supports(@NotNull @Nullable Class<?> clazz) {
        assert clazz != null;
        return NovaCompraForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NotNull @Nullable Object target, Errors errors) {
        if (errors.hasErrors())
            return;

        NovaCompraForm form = (NovaCompraForm) target;

        assert !Objects.isNull(form);
        if (form.temEstado()) {
            Pais pais = paisRepository.findById(form.getIdPais()).orElse(null);
            Estado estado = estadoRepository.findById(form.getIdEstado()).orElse(null);

            assert !Objects.isNull(estado);
            if (estado.naoPertenceA(pais)) {
                errors.rejectValue("idEstado", "Estado.idEstado", "Esse estado não pertence a esse país");
            }
        }

    }
}
