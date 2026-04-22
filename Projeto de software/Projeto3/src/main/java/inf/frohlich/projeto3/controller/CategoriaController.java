package inf.frohlich.projeto3.controller;

import inf.frohlich.projeto3.Service.CategoriaService;
import inf.frohlich.projeto3.model.Categoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/formCat")
    public String ExibirFormulario(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "formCat";
    }

    @PostMapping("/salvar")
    public String salvarCategoria(Categoria categoria) {
        categoriaService.salvar(categoria);
        return "redirect:/categoria/listar";
    }

    @GetMapping("/listar")
    public String listarCategoria(Model model) {
        List<Categoria> categorias = categoriaService.listarTodas();
        model.addAttribute("categorias", categorias);
        return "listaCat";
    }

    @GetMapping("/deletar/{id}")
    public String excluirCategoria(@PathVariable Integer id) {
        categoriaService.excluir(id);
        return "redirect:/categoria/listar";
    }

    @GetMapping("/confirmar-exclusao/{id}")
    public String confirmarExclusaoCategoria(@PathVariable Integer id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id);
        model.addAttribute("titulo", "Excluir categoria");
        model.addAttribute("mensagem", "Tem certeza que deseja excluir a categoria \"" + categoria.getNome() + "\"?");
        model.addAttribute("acaoConfirmar", "/categoria/deletar/" + id);
        model.addAttribute("acaoCancelar", "/categoria/listar");
        return "exclusao";
    }


    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Integer id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id);
        model.addAttribute("categoria", categoria);
        return "formCat";
    }
}
