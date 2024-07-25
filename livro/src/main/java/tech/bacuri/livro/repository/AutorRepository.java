package tech.bacuri.livro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.bacuri.livro.entity.Autor;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Integer> {
}
