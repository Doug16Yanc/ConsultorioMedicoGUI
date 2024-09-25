package repository;

import entities.Medico;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicoRepository {

    public void cadastrarMedico(Medico medico) throws SQLException {
        String sql = "INSERT INTO medico (NOME, CRM, SENHA, EMAIL, TELEFONE, ESPECIALIDADE) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getCRM());
            stmt.setString(3, medico.getSenha());
            stmt.setString(4, medico.getEmail());
            stmt.setString(5, medico.getTelefone());
            stmt.setString(6, medico.getEspecialidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar médico: " + e.getMessage());
            throw new SQLException("Erro ao tentar cadastrar médico no banco de dados.", e);
        }
    }
}
