package tech.bacuri.livro.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tech.bacuri.livro.controller.dto.autor.NovoAutorForm;
import tech.bacuri.livro.repository.AutorRepository;

@RequiredArgsConstructor
@Component
public class ProibeEmailDuplicadoAutorValidator implements Validator {

    private final AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoAutorForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        NovoAutorForm form = (NovoAutorForm) target;

        if (autorRepository.existsByEmail(form.getEmail()))
            errors.rejectValue("email", null, "Já existe outro autor com esse mesmo e-mail");
    }
}
