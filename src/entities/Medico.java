package entities;

public class Medico {
    private String nome;
    private String CRM;
    private String senha;
    private String email;
    private String telefone;
    private Especialidade especialidade;

    public Medico() {}

    public Medico(String nome, String CRM, String senha, String email, String telefone, Especialidade especialidade) {
        this.nome = nome;
        this.CRM = CRM;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.especialidade = especialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}
