package entities;

public class Paciente {
    public int id;
    public String nome;
    public String email;
    public String telefone;
    public String login;
    public String senha;

    public Paciente() {

    }
    public Paciente(int id, String nome, String email, String telefone, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;

    }

}
