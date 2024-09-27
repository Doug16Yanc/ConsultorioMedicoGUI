package repository;

import entities.Paciente;

import java.sql.SQLException;
import java.util.List;

public class PopularPacientes {

    public static void popularPacientes() {
        PacienteRepository pacienteRepository = new PacienteRepository();

        List<Paciente> pacientes = List.of(
            new Paciente("João Silva", "joao.silva@email.com", "123456789", "senha123", 123),
            new Paciente("Maria Oliveira", "maria.oliveira@email.com", "987654321", "senha456", 145),
            new Paciente("Pedro Santos", "pedro.santos@email.com", "456789123", "senha789", 567),
            new Paciente("Ana Clara", "ana.clara@email.com", "321654987", "senha101", 789),
            new Paciente("Lucas Almeida", "lucas.almeida@email.com", "654321789", "senha202", 908)
        );

        for (Paciente paciente : pacientes) {
            try {
                pacienteRepository.cadastrarPaciente(paciente);
                System.out.println("Paciente cadastrado: " + paciente.getNome());
            } catch (SQLException e) {
                System.err.println("Erro ao cadastrar paciente " + paciente.getNome() + ": " + e.getMessage());
            }
        }
    }
}
