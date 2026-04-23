package inf.frohlich.prova1pw.mapper;

import inf.frohlich.prova1pw.dto.MedicoDTORequest;
import inf.frohlich.prova1pw.dto.MedicoDTOResponse;
import inf.frohlich.prova1pw.model.Medico;

import java.util.List;

public class MedicoMapper {

	private MedicoMapper() {
	}

	public static Medico toEntity(MedicoDTORequest dto) {
		if (dto == null) {
			return null;
		}
		return new Medico(dto.nome(), dto.especialidade(), dto.crm());
	}

	public static MedicoDTOResponse toResponse(Medico medico) {
		if (medico == null) {
			return null;
		}
		// O DTO de resposta expõe só o que faz sentido devolver para a API.
		return new MedicoDTOResponse(medico.getNome(), medico.getCrm());
	}

	public static List<MedicoDTOResponse> toResponseList(List<Medico> medicos) {
		if (medicos == null) {
			return List.of();
		}
		return medicos.stream().map(MedicoMapper::toResponse).toList();
	}
}
