package inf.frohlich.prova1pw.service;

import inf.frohlich.prova1pw.dto.MedicoDTORequest;
import inf.frohlich.prova1pw.dto.MedicoDTOResponse;
import inf.frohlich.prova1pw.dto.UpdateMedicoDTO;
import inf.frohlich.prova1pw.mapper.MedicoMapper;
import inf.frohlich.prova1pw.model.Medico;
import inf.frohlich.prova1pw.repository.MedicoRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public MedicoDTOResponse salvar(MedicoDTORequest dto) {
        if (dto == null || dto.nome() == null || dto.nome().isBlank()
                || dto.especialidade() == null || dto.especialidade().isBlank()
                || dto.crm() == null || dto.crm().isBlank()) {
            throw new IllegalArgumentException("Nome, especialidade e CRM são obrigatórios.");
        }

        Medico medico = MedicoMapper.toEntity(dto);
        Medico medicoSalvo = medicoRepository.save(medico);
        return MedicoMapper.toResponse(medicoSalvo);
    }

	// Atualização parcial: só altera os campos que vierem preenchidos no DTO.
	// Isso permite reaproveitar o mesmo endpoint para correções pontuais sem apagar dados existentes.
    public MedicoDTOResponse atualizar(UpdateMedicoDTO dto) {
        if (dto == null || dto.id() == null) {
            throw new IllegalArgumentException("ID do médico é obrigatório.");
        }

        Medico medico = medicoRepository.findById(dto.id())
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado."));

        if (dto.nome() != null && !dto.nome().isBlank()) {
            medico.setNome(dto.nome());
        }
        if (dto.especialidade() != null && !dto.especialidade().isBlank()) {
            medico.setEspecialidade(dto.especialidade());
        }
        if (dto.crm() != null && !dto.crm().isBlank()) {
            medico.setCrm(dto.crm());
        }

        Medico atualizado = medicoRepository.save(medico);
        return MedicoMapper.toResponse(atualizado);
    }
}