package inf.frohlich.projapi.dto;

import java.util.List;

public record UsuarioDTOResponse(String nome, String email, List<TelefoneDTOResponse> telefones) {
}
