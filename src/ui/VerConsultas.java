package ui;

import entities.Consulta;
import entities.Medico;
import entities.Paciente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static utilities.Fonts.JET_BRAINS_MONO;

public class VerConsultas extends JFrame {
    private final Paciente paciente;
    private final List<Consulta> consultas;

    public VerConsultas(Paciente paciente, List<Consulta> consultas) {
        this.paciente = paciente;
        this.consultas = consultas;

        setTitle("Ver consultas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // listarConsultasPorPaciente(consultas);
        setVisible(true);
    }
    /*

    public void listarConsultasPorPaciente(List<Consulta> consultas) {
        if (!consultas.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Consulta consulta : consultas) {
                sb.append(mostraConsultaPaciente(consulta)).append("\n\n");
            }
            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
            JOptionPane.showMessageDialog(null, scrollPane, "Consultas por paciente", JOptionPane.INFORMATION_MESSAGE);
        } else if (consultas == null) {
            JOptionPane.showMessageDialog(null, "Nenhuma consulta marcada ainda.", "Consultas por paciente", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public String mostraConsultaPaciente(Consulta consulta) {
        return "********************************************************\n" +
                "Identificador do(a) paciente : " + consulta.getPaciente().getId() + "\n" +
                "Nome do(a) paciente : " + consulta.getPaciente().getNome() + "\n" +
                "Email : " + consulta.getPaciente().getEmail() + "\n" +
                "Telefone : " + consulta.getPaciente().getTelefone() + "\n" +
                "Identificador da consulta : " + consulta.getId() + "\n" +
                "Motivo da consulta : " + consulta.getMotivo() + "\n" +
                "Data e hora da consulta : " + consulta.getAgora() + "\n" +
                "********************************************************\n";
    }
*/
}