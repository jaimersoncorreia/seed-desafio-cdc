package tech.bacuri.livro.repository;

import org.springframework.data.repository.CrudRepository;
import tech.bacuri.livro.entity.Compra;

public interface CompraRepository extends CrudRepository<Compra, Integer> {
}
