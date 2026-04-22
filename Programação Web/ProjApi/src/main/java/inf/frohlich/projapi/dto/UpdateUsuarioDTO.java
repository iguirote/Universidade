package inf.frohlich.projapi.dto;

import java.util.List;

public record UpdateUsuarioDTO(Long id, String nome, String email, List<UpdateTelefoneDTO> telefones) {

}
