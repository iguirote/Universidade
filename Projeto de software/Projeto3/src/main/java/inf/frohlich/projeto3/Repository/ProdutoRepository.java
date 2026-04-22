package inf.frohlich.projeto3.Repository;

import inf.frohlich.projeto3.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository  extends JpaRepository<Produto, Integer> {
}
