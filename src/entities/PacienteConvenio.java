package entities;

public class PacienteConvenio extends Paciente {
    private int codigoConvenio;

    public PacienteConvenio() {
    }

    public PacienteConvenio(int id, String nome, String email, String telefone, String senha, int codigoConvenio) {
        super(nome, email, telefone, senha);
        this.codigoConvenio = codigoConvenio;
    }

    public int getCodigoConvenio() {
        return codigoConvenio;
    }

    public void setCodigoConvenio(int codigoConvenio) {
        this.codigoConvenio = codigoConvenio;
    }
}
