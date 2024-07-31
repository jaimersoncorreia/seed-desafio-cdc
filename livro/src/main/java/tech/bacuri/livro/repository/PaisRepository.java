package tech.bacuri.livro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.bacuri.livro.entity.Pais;

@Repository
public interface PaisRepository extends CrudRepository<Pais, Integer> {
}
