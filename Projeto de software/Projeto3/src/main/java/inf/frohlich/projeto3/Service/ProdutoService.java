package inf.frohlich.projeto3.Service;


import inf.frohlich.projeto3.Repository.ProdutoRepository;
import inf.frohlich.projeto3.model.Produto;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvar(Produto produto) {
        if(produto.getNome() == null || produto.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        if (produto.getCategoria() == null || produto.getCategoria().getId() == null) {
            throw new IllegalArgumentException("Categoria invalida");
        }
        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void excluir(Integer id) {
        produtoRepository.deleteById(id);
    }
}
