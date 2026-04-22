package inf.frohlich.projeto3.controller;


import inf.frohlich.projeto3.Service.CategoriaService;
import inf.frohlich.projeto3.Service.ProdutoService;
import inf.frohlich.projeto3.model.Categoria;
import inf.frohlich.projeto3.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    public ProdutoController(ProdutoService produtoService, CategoriaService categoriaService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/formulario")
    public String exibirFormulario(Model model) {
        Produto produto = new Produto();
        produto.setCategoria(new Categoria());
        model.addAttribute("produto", produto);
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "formulario";
    }

    @PostMapping("/salvar")
    public String salvarProduto(Produto produto) {
        produtoService.salvar(produto);
        return "redirect:/produto/listar";
    }
    @GetMapping("/listar")
    public String listarProduto(Model model) {
        List<Produto> produtos = produtoService.listarTodos();
        model.addAttribute("produtos", produtos);
        return "lista";
    }

    @GetMapping("/deletar/{id}")
    public String excluirProduto(@PathVariable Integer id) {
        produtoService.excluir(id);
        return "redirect:/produto/listar";
    }

    @GetMapping("/confirmar-exclusao/{id}")
    public String confirmarExclusaoProduto(@PathVariable Integer id, Model model) {
        Produto produto = produtoService.buscarPorId(id);
        model.addAttribute("titulo", "Excluir produto");
        model.addAttribute("mensagem", "Tem certeza que deseja excluir o produto \"" + produto.getNome() + "\"?");
        model.addAttribute("acaoConfirmar", "/produto/deletar/" + id);
        model.addAttribute("acaoCancelar", "/produto/listar");
        return "exclusao";
    }


    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Integer id, Model model) {
        Produto produto = produtoService.buscarPorId(id);
        model.addAttribute("produto", produto);
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "formulario";
    }
}
