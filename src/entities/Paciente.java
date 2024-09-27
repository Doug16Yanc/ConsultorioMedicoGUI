package entities;

import java.util.Random;

public class Paciente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private int codigo;

    public Paciente() {

    }

    public Paciente(String nome, String email, String telefone, String senha, int codigo) {
        this.id = new Random().nextInt();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.codigo = codigo;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
