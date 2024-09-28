package ui;

import entities.Consulta;
import entities.Medico;
import entities.Paciente;
import repository.PacienteRepository;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

import static utilities.Fonts.JET_BRAINS_MONO;

public class VerConsultas extends JFrame {

    public VerConsultas(Paciente paciente) {
        setTitle("Ver Consultas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            listarConsultasPorPaciente(paciente);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar consultas: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(true);
    }

    public void listarConsultasPorPaciente(Paciente paciente) throws SQLException {
        PacienteRepository pr = new PacienteRepository();
        List<Consulta> consultas = pr.pegarConsultasPorPaciente(paciente);

        if (consultas != null && !consultas.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Consulta consulta : consultas) {
                sb.append(mostraConsultaPaciente(consulta)).append("\n\n");
            }

            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 17));
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(700, 500));

            add(scrollPane, BorderLayout.CENTER);
        } else {
            JOptionPane.showMessageDialog(this, "Nenhuma consulta marcada ainda.", "Consultas por Paciente", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public String mostraConsultaPaciente(Consulta consulta) {
        Medico medico = consulta.getMedico();
        return "----------------------------------------------------------------------\n" +
                "Identificador do(a) paciente: " + consulta.getPaciente().getId() + "\n" +
                "Nome do(a) paciente: " + consulta.getPaciente().getNome() + "\n" +
                "Email: " + consulta.getPaciente().getEmail() + "\n" +
                "Telefone: " + consulta.getPaciente().getTelefone() + "\n" +
                "Identificador da consulta: " + consulta.getId() + "\n" +
                "Motivo da consulta: " + consulta.getMotivo() + "\n" +
                "Data e hora da consulta: " + consulta.getAgora() + "\n" +
                "Médico responsável: " + (medico != null ? medico.getNome() : "Não disponível") + "\n" +
                "Status: " + (consulta.getStatus() ? "Concluída" : "Pendente");
    }
}
