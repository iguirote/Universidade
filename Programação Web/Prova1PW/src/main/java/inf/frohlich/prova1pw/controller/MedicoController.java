package inf.frohlich.prova1pw.controller;

import inf.frohlich.prova1pw.dto.MedicoDTORequest;
import inf.frohlich.prova1pw.dto.MedicoDTOResponse;
import inf.frohlich.prova1pw.dto.UpdateMedicoDTO;
import inf.frohlich.prova1pw.mapper.MedicoMapper;
import inf.frohlich.prova1pw.repository.MedicoRepository;
import inf.frohlich.prova1pw.service.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {
    private final MedicoRepository medicoRepository;
    private final MedicoService medicoService;

    public MedicoController(MedicoRepository medicoRepository, MedicoService medicoService) {
        this.medicoRepository = medicoRepository;
        this.medicoService = medicoService;
    }

    @PostMapping
    public ResponseEntity<MedicoDTOResponse> salvar(@RequestBody MedicoDTORequest medicoDTO) {
        MedicoDTOResponse response = medicoService.salvar(medicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<MedicoDTOResponse> listar() {
        return MedicoMapper.toResponseList(medicoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTOResponse> buscarPorId(@PathVariable Long id) {
        return medicoRepository.findById(id)
                // Se encontrou, converte Medico -> MedicoDTOResponse; se não, continua vazio.
                .map(MedicoMapper::toResponse)
                // Se o DTO existe, embrulha em ResponseEntity 200 OK.
                .map(ResponseEntity::ok)
                // Se nada foi encontrado, devolve 404 Not Found.
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTOResponse> atualizar(@PathVariable Long id, @RequestBody UpdateMedicoDTO dto) {

        UpdateMedicoDTO dtoComId = new UpdateMedicoDTO(id, dto.nome(), dto.especialidade(), dto.crm());
        return ResponseEntity.ok(medicoService.atualizar(dtoComId));
    }

    // Antes de excluir, o controller verifica se o recurso existe para devolver 404 quando necessário.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        if (!medicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        medicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
