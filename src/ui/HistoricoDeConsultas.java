package ui;

import entities.Consulta;
import entities.Medico;
import repository.MedicoRepository;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class HistoricoDeConsultas extends JFrame {

    public HistoricoDeConsultas(Medico medico) {
        setTitle("Histórico de Consultas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            listarHistoricoDeConsultas(medico);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar histórico de consultas: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }

        setVisible(true);
    }

    public void listarHistoricoDeConsultas(Medico medico) throws SQLException {
        MedicoRepository mr = new MedicoRepository();
        List<Consulta> consultas = mr.pegarConsultasPorMedico(medico);

        if (!consultas.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Consulta consulta : consultas) {
                sb.append(mostraConsultaPaciente(consulta)).append("\n\n");
            }

            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setFont(new Font("JetBrains Mono", Font.PLAIN, 17));
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            JScrollPane scrollPane = new JScrollPane(textArea);
            add(scrollPane, BorderLayout.CENTER);
        } else {
            JOptionPane.showMessageDialog(this, "Nenhuma consulta marcada ainda.", "Histórico de Consultas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public String mostraConsultaPaciente(Consulta consulta) {
        return "----------------------------------------------------------------------\n" +
                "Identificador do(a) paciente: " + consulta.getPaciente().getId() + "\n" +
                "Nome do(a) paciente: " + consulta.getPaciente().getNome() + "\n" +
                "Email: " + consulta.getPaciente().getEmail() + "\n" +
                "Telefone: " + consulta.getPaciente().getTelefone() + "\n" +
                "Identificador da consulta: " + consulta.getId() + "\n" +
                "Motivo da consulta: " + consulta.getMotivo() + "\n" +
                "Data e hora da consulta: " + consulta.getAgora();
    }
}
