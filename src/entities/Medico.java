package entities;

import java.util.Random;

public class Medico {
    private int id;
    private String nome;
    private String CRM;
    private String senha;
    private String email;
    private String telefone;
    private String especialidade;

    public Medico() {}

    public Medico(String nome, String CRM, String senha, String email, String telefone, String especialidade) {
        this.id = new Random().nextInt();
        this.nome = nome;
        this.CRM = CRM;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.especialidade = especialidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
