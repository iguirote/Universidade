package inf.frohlich.prova1pw.mapper;

import inf.frohlich.prova1pw.dto.ConsultaDTORequest;
import inf.frohlich.prova1pw.dto.ConsultaDTOResponse;
import inf.frohlich.prova1pw.dto.MedicoDTOResponse;
import inf.frohlich.prova1pw.dto.PacienteDTOResponse;
import inf.frohlich.prova1pw.model.Consulta;
import inf.frohlich.prova1pw.model.Medico;
import inf.frohlich.prova1pw.model.Paciente;

import java.util.List;

public class ConsultaMapper {

	private ConsultaMapper() {
	}

	public static Consulta toEntity(ConsultaDTORequest dto) {
		if (dto == null) {
			return null;
		}

		Consulta consulta = new Consulta();
		consulta.setData(dto.data());
		consulta.setPreco(dto.preco());
		consulta.setAnotacoes(dto.anotacoes());
		consulta.setStatus(dto.status());
		// Em vez de buscar o paciente/médico aqui no banco, o service valida a existência.
		// O mapper só referencia o relacionamento pelo id para manter a responsabilidade separada.
		consulta.setPaciente(dto.idPaciente() == null ? null : new Paciente(dto.idPaciente(), null, null, null));
		consulta.setMedico(dto.idMedico() == null ? null : new Medico(dto.idMedico(), null, null, null));
		return consulta;
	}

	public static ConsultaDTOResponse toResponse(Consulta consulta) {
		if (consulta == null) {
			return null;
		}

		PacienteDTOResponse pacienteDTO = consulta.getPaciente() != null ?
				new PacienteDTOResponse(
						consulta.getPaciente().getId(),
						consulta.getPaciente().getNome(),
						consulta.getPaciente().getEmail(),
						consulta.getPaciente().getCpf()
				) : null;

		MedicoDTOResponse medicoDTO = consulta.getMedico() != null ?
				new MedicoDTOResponse(
						consulta.getMedico().getNome(),
						consulta.getMedico().getCrm()
				) : null;

		return new ConsultaDTOResponse(
				consulta.getId(),
				pacienteDTO,
				medicoDTO,
				consulta.getData(),
				consulta.getStatus(),
				consulta.getPreco(),
				consulta.getAnotacoes()
		);
	}

	public static List<ConsultaDTOResponse> toResponseList(List<Consulta> consultas) {
		if (consultas == null) {
			return List.of();
		}
		return consultas.stream().map(ConsultaMapper::toResponse).toList();
	}
}
