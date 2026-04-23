package inf.frohlich.prova1pw.mapper;

import inf.frohlich.prova1pw.dto.PacienteDTORequest;
import inf.frohlich.prova1pw.dto.PacienteDTOResponse;
import inf.frohlich.prova1pw.model.Paciente;

import java.util.List;

public class PacienteMapper {

	private PacienteMapper() {
	}

	public static Paciente toEntity(PacienteDTORequest dto) {
		if (dto == null) {
			return null;
		}
		return new Paciente(dto.nome(), dto.email(), dto.cpf());
	}

	public static PacienteDTOResponse toResponse(Paciente paciente) {
		if (paciente == null) {
			return null;
		}
		// No retorno, o id volta para o cliente porque agora a entidade já foi persistida.
		return new PacienteDTOResponse(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
	}

	public static List<PacienteDTOResponse> toResponseList(List<Paciente> pacientes) {
		if (pacientes == null) {
			return List.of();
		}
		return pacientes.stream().map(PacienteMapper::toResponse).toList();
	}
}

