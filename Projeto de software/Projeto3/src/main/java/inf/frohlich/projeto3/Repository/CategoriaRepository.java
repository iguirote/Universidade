package inf.frohlich.projeto3.Repository;

import inf.frohlich.projeto3.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
