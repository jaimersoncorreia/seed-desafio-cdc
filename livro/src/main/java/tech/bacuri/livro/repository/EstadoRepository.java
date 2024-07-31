package tech.bacuri.livro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.bacuri.livro.entity.Estado;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Integer> {
}
