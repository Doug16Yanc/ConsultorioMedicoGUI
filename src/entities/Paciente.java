package entities;

public class Paciente extends Pessoa{
    private int codigo;

    public Paciente() {
    }

    public Paciente(String nome, String email, String telefone, String senha, int codigo) {
        super(nome, email, telefone, senha);
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
