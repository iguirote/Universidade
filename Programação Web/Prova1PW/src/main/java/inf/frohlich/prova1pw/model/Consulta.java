package inf.frohlich.prova1pw.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private LocalDateTime data;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
    @Column(nullable = false, length = 100)
    private String anotacoes;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusConsulta status;
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    public Consulta() {
        this.status = StatusConsulta.AGENDADA;
    }

    public Consulta(LocalDateTime data, BigDecimal preco, String anotacoes, StatusConsulta status, Paciente paciente, Medico medico) {
        this.data = data;
        this.preco = preco;
        this.anotacoes = anotacoes;
        this.status = status;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Consulta(long id, LocalDateTime data, BigDecimal preco, String anotacoes, StatusConsulta status, Paciente paciente, Medico medico) {

        this.id = id;
        this.data = data;
        this.preco = preco;
        this.anotacoes = anotacoes;
        this.status = status;
        this.paciente = paciente;
        this.medico = medico;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
