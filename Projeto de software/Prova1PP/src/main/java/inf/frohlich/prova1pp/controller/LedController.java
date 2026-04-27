package inf.frohlich.prova1pp.controller;

import inf.frohlich.prova1pp.ConexaoPorta;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/led")
public class LedController {

    private final ConexaoPorta conexao;

    public LedController(ConexaoPorta conexao) {
        this.conexao = conexao;
    }

    @PostMapping("/on")
    public String ligarLed() {
        conexao.enviaDados('1');
        return "redirect:/index.html";
    }
    @PostMapping("/off")
    public String desligarLed() {
        conexao.enviaDados('0');
        return "redirect:/index.html";
    }
}

