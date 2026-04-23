package inf.frohlich.prova1pw.repository;

import inf.frohlich.prova1pw.model.Consulta;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
	boolean existsByMedico_IdAndData(Long medicoId, LocalDateTime data);

	boolean existsByPaciente_IdAndData(Long pacienteId, LocalDateTime data);
}
