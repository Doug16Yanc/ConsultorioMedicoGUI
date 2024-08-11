package entities;

public class Medico {
    public int id;
    public String nome;
    public String CRM;
    public String especialidade;
    String sexo;

    public Medico() {}

    public Medico(int id, String nome, String CRM, String especialidade, String sexo) {
        this.id = id;
        this.nome = nome;
        this.CRM = CRM;
        this.especialidade = especialidade;
        this.sexo = sexo;
    }
}
