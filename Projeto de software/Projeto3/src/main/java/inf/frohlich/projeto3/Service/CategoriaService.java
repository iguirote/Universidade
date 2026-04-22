package inf.frohlich.projeto3.Service;

import inf.frohlich.projeto3.Repository.CategoriaRepository;
import inf.frohlich.projeto3.model.Categoria;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria salvar(Categoria categoria) {
        if(categoria.getNome() == null || categoria.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da categoria é obrigatório.");
        }
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public void excluir(Integer id) {
        categoriaRepository.deleteById(id);
    }
}

