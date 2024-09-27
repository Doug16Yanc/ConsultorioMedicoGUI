package repository;

import entities.Consulta;
import entities.Medico;
import entities.Paciente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            System.err.println("Erro ao cadastrar paciente: " + e.getMessage());
            throw new SQLException(e.getMessage());
        }
    }

    public Paciente buscarPaciente(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM paciente WHERE EMAIL = ? AND SENHA = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setSenha(rs.getString("senha"));
                return paciente;
            } else {
                return null;
            }
        }
    }

    public List<Consulta> pegarConsultasPorPaciente(Paciente paciente) throws SQLException {
        String sql = """
        SELECT c.*, m.id as medico_id 
        FROM consulta c 
        JOIN paciente p ON p.id = c.paciente_id 
        JOIN medico m ON m.id = c.medico_id 
        WHERE p.id = ?
    """;

        List<Consulta> consultas = new ArrayList<>();

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            MedicoRepository medicoRepository = new MedicoRepository();
            stmt.setInt(1, paciente.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(UUID.fromString(rs.getString("id")));
                consulta.setMotivo(rs.getString("motivo"));
                consulta.setAgora(rs.getTimestamp("agora"));
                consulta.setPaciente(paciente);
                consulta.setStatus(rs.getBoolean("status"));

                int medicoId = rs.getInt("medico_id");

                Medico medico = medicoRepository.buscarMedicoById(medicoId);
                consulta.setMedico(medico);

                consultas.add(consulta);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar consultas: " + e.getMessage());
            throw new SQLException(e.getMessage());
        }
        return consultas;
    }

    public Paciente buscarPacienteById(int id) throws SQLException {
        String sql = "SELECT * FROM paciente WHERE ID = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(id);
                paciente.setNome(rs.getString("nome"));
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setSenha(rs.getString("senha"));
                return paciente;
            } else {
                return null;
            }
        }
    }

    public List<Paciente> buscarPacientesPorMedico(Medico medico) throws SQLException {
        String sql = """
    SELECT DISTINCT p.*
    FROM paciente p
    JOIN consulta c ON p.id = c.paciente_id
    WHERE c.medico_id = ?
    """;

        List<Paciente> pacientes = new ArrayList<>();

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, medico.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setSenha(rs.getString("senha"));
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar pacientes: " + e.getMessage());
            throw new SQLException(e.getMessage());
        }

        return pacientes;
    }

}
