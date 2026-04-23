package inf.frohlich.prova1pw.repository;

import inf.frohlich.prova1pw.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
