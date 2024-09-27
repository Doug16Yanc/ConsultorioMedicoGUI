package entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class Consulta {
    private UUID id;
    private String motivo;
    private Timestamp agora;
    private Paciente paciente;
    private Medico medico;
    private boolean status;

    public Consulta() {

    }

    public Consulta(String motivo, Timestamp agora, Paciente paciente, Medico medico) {
        this.id = UUID.randomUUID();
        this.motivo = motivo;
        this.agora = agora;
        this.paciente = paciente;
        this.medico = medico;
        this.status = false;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Timestamp getAgora() {
        return agora;
    }

    public void setAgora(Timestamp agora) {
        this.agora = agora;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
