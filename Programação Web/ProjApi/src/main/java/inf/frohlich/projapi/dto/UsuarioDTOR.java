package inf.frohlich.projapi.dto;

import java.util.List;

public record UsuarioDTOR(String nome, String email, String senha, String confirmarSenha, List<TelefoneDTORequest> telefones) {
}
