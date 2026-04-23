package inf.frohlich.prova1pw.service;

import inf.frohlich.prova1pw.dto.ConsultaDTORequest;
import inf.frohlich.prova1pw.dto.ConsultaDTOResponse;
import inf.frohlich.prova1pw.mapper.ConsultaMapper;
import inf.frohlich.prova1pw.model.Consulta;
import inf.frohlich.prova1pw.repository.ConsultaRepository;
import inf.frohlich.prova1pw.repository.MedicoRepository;
import inf.frohlich.prova1pw.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ConsultaService {

	private final ConsultaRepository consultaRepository;
	private final PacienteRepository pacienteRepository;
	private final MedicoRepository medicoRepository;

	public ConsultaService(ConsultaRepository consultaRepository,
						   PacienteRepository pacienteRepository,
						   MedicoRepository medicoRepository) {
		this.consultaRepository = consultaRepository;
		this.pacienteRepository = pacienteRepository;
		this.medicoRepository = medicoRepository;
	}

	public ConsultaDTOResponse salvar(ConsultaDTORequest dto) {
		validar(dto);

		if (!pacienteRepository.existsById(dto.idPaciente())) {
			throw new IllegalArgumentException("Paciente informado não existe.");
		}
		if (!medicoRepository.existsById(dto.idMedico())) {
			throw new IllegalArgumentException("Médico informado não existe.");
		}
		if (consultaRepository.existsByPaciente_IdAndData(dto.idPaciente(), dto.data())) {
			throw new IllegalArgumentException("O paciente já possui uma consulta nesse horário.");
		}
		if (consultaRepository.existsByMedico_IdAndData(dto.idMedico(), dto.data())) {
			throw new IllegalArgumentException("O médico já possui uma consulta nesse horário.");
		}

		Consulta consulta = ConsultaMapper.toEntity(dto);
		Consulta salva = consultaRepository.save(consulta);
		return ConsultaMapper.toResponse(salva);
	}

	private void validar(ConsultaDTORequest dto) {
		if (dto == null) {
			throw new IllegalArgumentException("Os dados da consulta são obrigatórios.");
		}
		if (dto.data() == null) {
			throw new IllegalArgumentException("A data da consulta é obrigatória.");
		}
		if (dto.data().isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Não é permitido agendar consulta no passado.");
		}
		if (dto.status() == null) {
			throw new IllegalArgumentException("O status deve ser informado no cadastro.");
		}
		if (dto.preco() == null || dto.preco().compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("O valor da consulta deve ser maior que zero.");
		}
		if (dto.anotacoes() == null || dto.anotacoes().isBlank()) {
			throw new IllegalArgumentException("As anotações da consulta são obrigatórias.");
		}
		if (dto.idPaciente() == null) {
			throw new IllegalArgumentException("O paciente deve ser informado.");
		}
		if (dto.idMedico() == null) {
			throw new IllegalArgumentException("O médico deve ser informado.");
		}
	}
}
