package tech.bacuri.livro.annotation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.util.List;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {
    private String domainAtribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        this.domainAtribute = constraintAnnotation.fieldName();
        this.klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String sql = String.format("select 1 from %s where %s = :value", klass.getSimpleName(), domainAtribute);
        Query query = manager.createQuery(sql);
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, String.format("Foi encontrado mais de uma entidade %s com o mesmo nome", klass.getSimpleName()));
        return list.isEmpty();
    }
}
