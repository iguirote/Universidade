package inf.frohlich.projapi.Controller;


import inf.frohlich.projapi.Repository.UsuarioRepository;
import inf.frohlich.projapi.dto.UpdateUsuarioDTO;
import inf.frohlich.projapi.dto.UsuarioDTOR;
import inf.frohlich.projapi.dto.UsuarioDTOResponse;
import inf.frohlich.projapi.mapper.UsuarioMapper;
import inf.frohlich.projapi.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTOResponse> salvar(@RequestBody UsuarioDTOR usuarioDTO) {
        // Entrada via DTO para não acoplar o contrato HTTP à entidade JPA
        UsuarioDTOResponse response = usuarioService.salvar(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<UsuarioDTOResponse> listar() {
        // Lista já em DTO pra não vazar a entidade inteira pro cliente
        return UsuarioMapper.toResponseList(usuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTOResponse> buscarPorId(@PathVariable Long id) {
        // Busca direta por id e devolve 404 quando não encontrar.
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<UsuarioDTOResponse> atualizar(@RequestBody UpdateUsuarioDTO dto) {
        // Update fica no service pra manter a regra de negócio centralizada
        return ResponseEntity.ok(usuarioService.atualizar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        // Retorna 404 quando não existe e 204 quando remove com sucesso
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
