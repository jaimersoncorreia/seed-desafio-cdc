package tech.bacuri.livro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.bacuri.livro.entity.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
    boolean existsByNome(String nome);
}
