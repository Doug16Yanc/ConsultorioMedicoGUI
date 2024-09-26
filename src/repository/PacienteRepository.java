package repository;

import entities.Medico;
import entities.Paciente;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PacienteRepository {

    public void cadastrarPaciente(Paciente paciente) throws SQLException {
        String sql = "INSERT INTO paciente (NOME, EMAIL, TELEFONE, SENHA) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getEmail());
            stmt.setString(3, paciente.getTelefone());
            stmt.setString(4, paciente.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar médico: " + e.getMessage());
            throw new SQLException("Erro ao tentar cadastrar médico no banco de dados.", e);
        }
    }
}
