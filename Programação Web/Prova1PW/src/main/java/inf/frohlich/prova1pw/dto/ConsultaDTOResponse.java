package inf.frohlich.prova1pw.dto;

import inf.frohlich.prova1pw.model.StatusConsulta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ConsultaDTOResponse(Long id, PacienteDTOResponse paciente, MedicoDTOResponse medico,
                                  LocalDateTime data, StatusConsulta status,
                                  BigDecimal preco, String anotacoes) {
}
