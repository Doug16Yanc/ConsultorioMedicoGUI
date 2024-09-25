package repository;

import entities.Medico;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicoRepository {

    public void cadastrarMedico(Medico medico) throws SQLException {
        String sql = "INSERT INTO MEDICO (NOME, CRM, SENHA, EMAIL, TELEFONE, ESPECIALIDADE) VALUES (? ? ? ? ? ?)";

        PreparedStatement stmt = null;

        try {
            stmt = DatabaseConnection.getConnection().prepareStatement(sql);
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getCRM());
            stmt.setString(3, medico.getSenha());
            stmt.setString(4, medico.getEmail());
            stmt.setString(5, medico.getTelefone());
            stmt.setString(6, medico.getEspecialidade().getNomeEspecialidade());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
