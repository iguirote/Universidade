package inf.frohlich.projapi.service;

import inf.frohlich.projapi.Repository.UsuarioRepository;
import inf.frohlich.projapi.dto.UpdateUsuarioDTO;
import inf.frohlich.projapi.dto.UpdateTelefoneDTO;
import inf.frohlich.projapi.dto.UsuarioDTOR;
import inf.frohlich.projapi.dto.UsuarioDTOResponse;
import inf.frohlich.projapi.mapper.TelefoneMapper;
import inf.frohlich.projapi.mapper.UsuarioMapper;
import inf.frohlich.projapi.model.Telefone;
import inf.frohlich.projapi.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTOResponse salvar(UsuarioDTOR dto) {
        // Validação de regra de negócio antes de persistir.
        if (dto.senha() == null || dto.confirmarSenha() == null || !dto.senha().equals(dto.confirmarSenha())) {
            throw new IllegalArgumentException("As senhas nao coincidem ou estao nulas.");
        }

        // Entrada via DTO -> entidade, saída sempre via DTO de resposta
        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toResponse(usuarioSalvo);
    }

    public UsuarioDTOResponse atualizar(UpdateUsuarioDTO dto) {
        // Atualização só acontece quando o id vem no payload
        if (dto == null || dto.id() == null) {
            throw new IllegalArgumentException("Id do usuário é obrigatório para atualizar.");
        }

        Usuario usuario = usuarioRepository.findById(dto.id())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (dto.nome() != null) {
            usuario.setNome(dto.nome());
        }

        if (dto.email() != null) {
            usuario.setEmail(dto.email());
        }

        if (dto.telefones() != null) {
            // Recria a lista com base no payload pra manter o estado previsível
            var telefonesAtuais = usuario.getTelefones() == null ? new ArrayList<Telefone>() : usuario.getTelefones();
            var novosTelefones = new ArrayList<Telefone>();

            for (UpdateTelefoneDTO telefoneDTO : dto.telefones()) {
                Telefone telefone = null;

                if (telefoneDTO.id() != null) {
                    telefone = telefonesAtuais.stream()
                            .filter(t -> Integer.valueOf(t.getId()).equals(telefoneDTO.id()))
                            .findFirst()
                            .orElse(null);
                }

                if (telefone == null) {
                    telefone = new Telefone();
                }

                // Cada telefone novo ou existente passa por update
                TelefoneMapper.applyUpdate(telefone, telefoneDTO);
                telefone.setUsuario(usuario);
                novosTelefones.add(telefone);
            }

            usuario.setTelefones(novosTelefones);
        }

        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toResponse(salvo);
    }
}
