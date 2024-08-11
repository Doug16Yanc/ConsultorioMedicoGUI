package entities;

import java.util.UUID;

public class Consulta {
    public UUID id;
    public String motivo;
    public String agora;
    public Paciente paciente;
    public Medico medico;

    public Consulta() {

    }
    public Consulta(UUID id, String motivo, String agora, Paciente paciente, Medico medico) {
        this.id = id;
        this.motivo = motivo;
        this.agora = agora;
        this.paciente = paciente;
        this.medico = medico;
    }
}
