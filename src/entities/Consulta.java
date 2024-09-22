package entities;

import java.util.Random;
import java.util.UUID;

public class Consulta {
    private UUID id;
    private String motivo;
    private String agora;
    private Paciente paciente;
    private Medico medico;

    public Consulta() {

    }
    public Consulta(String motivo, String agora, Paciente paciente, Medico medico) {
        this.id = UUID.randomUUID();
        this.motivo = motivo;
        this.agora = agora;
        this.paciente = paciente;
        this.medico = medico;
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

    public String getAgora() {
        return agora;
    }

    public void setAgora(String agora) {
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
}
