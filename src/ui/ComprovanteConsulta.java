package ui;

import entities.Consulta;
import ui.components.Button;

import javax.swing.*;

import java.awt.*;

import static utilities.Fonts.JET_BRAINS_MONO;

public class ComprovanteConsulta extends JFrame {

    private JTextArea comprovanteArea;

    public ComprovanteConsulta(Consulta consulta) {
        setTitle("Comprovante de Consulta");
        setSize(750, 350);
        setLocationRelativeTo(null);

        comprovanteArea = new JTextArea();
        comprovanteArea.setEditable(false);
        comprovanteArea.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 18));
        comprovanteArea.setText(gerarComprovante(consulta));

        JScrollPane scrollPane = new JScrollPane(comprovanteArea);

        Button fecharButton = new Button("Fechar");
        fecharButton.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        fecharButton.setBorder(BorderFactory.createEmptyBorder(17, 65, 17, 65));
        fecharButton.setBackground(new Color(0xFF001A));
        fecharButton.setForeground(Color.WHITE);
        fecharButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(fecharButton);

        setLayout(new BorderLayout());

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private String gerarComprovante(Consulta consulta) {
        return "\tCOMPROVANTE DE CONSULTA.\n\n" +
                "Código da consulta: " + consulta.getId() + "\n" +
                "Data e hora marcada: " + consulta.getAgora() + "\n" +
                "Identificador do(a) paciente: " + consulta.getPaciente().getId() + "\n" +
                "Nome do(a) paciente: " + consulta.getPaciente().getNome() + "\n" +
                "Paciente relata: " + consulta.getMotivo() + "\n" +
                "Identificador do(a) médico(a): " + consulta.getMedico().getId() + "\n" +
                "Nome do(a) médico(a): " + consulta.getMedico().getNome();
    }
}
