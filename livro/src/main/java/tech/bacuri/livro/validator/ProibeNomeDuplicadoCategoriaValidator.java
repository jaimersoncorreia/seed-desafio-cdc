package tech.bacuri.livro.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tech.bacuri.livro.controller.dto.categoria.NovaCategoriaForm;
import tech.bacuri.livro.repository.CategoriaRepository;

@Component
@RequiredArgsConstructor
public class ProibeNomeDuplicadoCategoriaValidator implements Validator {
    private final CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCategoriaForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        NovaCategoriaForm categoria = (NovaCategoriaForm) target;

        if (categoriaRepository.existsByNome(categoria.getNome())) {
            errors.rejectValue("nome", null, "Não pode haver nome duplicado");
        }
    }
}
