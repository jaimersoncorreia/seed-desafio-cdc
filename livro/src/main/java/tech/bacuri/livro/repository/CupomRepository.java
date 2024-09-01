package tech.bacuri.livro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.bacuri.livro.entity.Cupom;

@Repository
public interface CupomRepository extends CrudRepository<Cupom, Long> {
    Cupom getCupomByCodigo(String codigo);
}
