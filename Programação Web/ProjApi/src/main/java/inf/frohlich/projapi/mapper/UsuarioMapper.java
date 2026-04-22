package inf.frohlich.projapi.mapper;

import inf.frohlich.projapi.dto.UsuarioDTOR;
import inf.frohlich.projapi.dto.UsuarioDTOResponse;
import inf.frohlich.projapi.model.Telefone;
import inf.frohlich.projapi.model.Usuario;
import java.util.List;




public class UsuarioMapper {
    public static Usuario toEntity(UsuarioDTOR dto) {
        if (dto == null) {
            return null;
        }

        // Monta a entidade a partir do DTO de entrada
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());

        List<Telefone> telefones = TelefoneMapper.toEntityList(dto.telefones());
        // Mantém o lado dono do relacionamento para o JPA persistir corretamente.
        telefones.forEach(telefone -> telefone.setUsuario(usuario));
        usuario.setTelefones(telefones);
        return usuario;
    }

    public static UsuarioDTOResponse toResponse(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        // Evita expor senha e detalhes internos da entidade no retorno da API.
        return new UsuarioDTOResponse(usuario.getNome(), usuario.getEmail(), TelefoneMapper.toResponseList(usuario.getTelefones()));
    }

    public static List<UsuarioDTOResponse> toResponseList(List<Usuario> usuarios) {
        if (usuarios == null) {
            return  List.of();
        } else {
            // Converte em lista de resposta segura para o cliente
            return usuarios.stream()
                    .map(UsuarioMapper::toResponse)
                    .toList();
        }
    }
}
