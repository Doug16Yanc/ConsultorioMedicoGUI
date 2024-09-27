package entities;

public class Medico extends Pessoa {
    private String CRM;
    private String especialidade;

    public Medico(){}

    public Medico(String nome, String email, String telefone, String senha, String CRM, String especialidade) {
        super(nome, email, telefone, senha);
        this.CRM = CRM;
        this.especialidade = especialidade;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }


    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
