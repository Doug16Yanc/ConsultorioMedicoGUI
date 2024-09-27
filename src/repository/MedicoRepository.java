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

public class MedicoRepository {

    public void cadastrarMedico(Medico medico) throws SQLException {
        if (medicoExiste(medico.getCRM(), medico.getEmail())) {
            System.out.println("Médico já cadastrado com o CRM ou email fornecido.");
            return;
        }

        String sql = "INSERT INTO medico (NOME, CRM, SENHA, EMAIL, TELEFONE, ESPECIALIDADE) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getCRM());
            stmt.setString(3, medico.getSenha());
            stmt.setString(4, medico.getEmail());
            stmt.setString(5, medico.getTelefone());
            stmt.setString(6, medico.getEspecialidade());
            stmt.executeUpdate();
            System.out.println("Médico cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar médico: " + e.getMessage());
            throw new SQLException("Erro ao tentar cadastrar médico no banco de dados.", e);
        }
    }

    public boolean medicoExiste(String crm, String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM medico WHERE CRM = ? OR EMAIL = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, crm);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar se o médico já existe: " + e.getMessage());
            throw new SQLException("Erro ao verificar médico no banco de dados.", e);
        }

        return false;
    }


    public Medico buscarMedico(String crm, String senha) throws SQLException {
        String sql = "SELECT * FROM medico WHERE CRM = ? AND SENHA = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, crm);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCRM(rs.getString("crm"));
                medico.setSenha(rs.getString("senha"));
                medico.setEmail(rs.getString("email"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setEspecialidade(rs.getString("especialidade"));
                return medico;
            } else {
                return null;
            }
        }
    }

    public List<Consulta> pegarConsultasPorMedico(Medico medico) throws SQLException {
        PacienteRepository pacienteRepository = new PacienteRepository();

        String sql = """
        SELECT c.*, p.id as paciente_id
        FROM consulta c
        JOIN medico m ON m.id = c.medico_id
        JOIN paciente p ON p.id = c.paciente_id
        WHERE m.id = ?
    """;
        List<Consulta> consultas = new ArrayList<>();

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, medico.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(UUID.fromString(rs.getString("id")));
                consulta.setMotivo(rs.getString("motivo"));
                consulta.setAgora(rs.getTimestamp("agora"));

                int pacienteId = rs.getInt("paciente_id");
                Paciente paciente = pacienteRepository.buscarPacienteById(pacienteId);
                consulta.setPaciente(paciente);

                consulta.setMedico(medico);

                consultas.add(consulta);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar consultas: " + e.getMessage());
            throw new SQLException(e.getMessage());
        }

        return consultas;
    }


    public Medico buscarMedicoById(int id) throws SQLException {
        String sql = "SELECT * FROM medico WHERE ID = ?";
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCRM(rs.getString("crm"));
                medico.setSenha(rs.getString("senha"));
                medico.setEmail(rs.getString("email"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setEspecialidade(rs.getString("especialidade"));
                return medico;
            } else {
                return null;
            }
        }
    }

    public List<Medico> buscarTodosOsMedicos() throws SQLException {
        String sql = "SELECT * FROM medico";
        List<Medico> medicos = new ArrayList<>();

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCRM(rs.getString("crm"));
                medico.setSenha(rs.getString("senha"));
                medico.setEmail(rs.getString("email"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setEspecialidade(rs.getString("especialidade"));
                medicos.add(medico);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar todos os médicos: " + e.getMessage());
            throw new SQLException("Erro ao buscar médicos no banco de dados.", e);
        }

        return medicos;
    }
}
