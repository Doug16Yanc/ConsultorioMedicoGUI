package entities;

public class PacienteSUS extends Paciente {
    private int numeroSUS;

    public PacienteSUS() {
    }

    public PacienteSUS(int id, String nome, String email, String telefone, String senha, int codigo, int numeroSUS) {
        super(nome, email, telefone, senha, codigo);
        this.numeroSUS = numeroSUS;
    }

    public int getNumeroSUS() {
        return numeroSUS;
    }

    public void setNumeroSUS(int numeroSUS) {
        this.numeroSUS = numeroSUS;
    }
}
