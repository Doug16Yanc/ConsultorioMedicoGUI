package ui;

import entities.Consulta;
import entities.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static utilities.Fonts.JET_BRAINS_MONO;

public class EscolhaPaciente extends JFrame implements ActionListener {
    private JButton susBotao, convenioBotao;
    private JLabel boasVindas, sou;
    private Image backgroundImage;
    private Paciente paciente;
    private java.util.List<Consulta> consultas;

    public EscolhaPaciente(List<Consulta> consultas) {
        setTitle("Tipo de paciente");
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        JPanel panel = new BackgroundImagePanel("src/images/medico.png");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        boasVindas = new JLabel("Que tipo de paciente você é?");
        boasVindas.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.BOLD, 35));
        boasVindas.setForeground(Color.WHITE);
        add(boasVindas);

        sou = new JLabel("Eu sou...");
        sou.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.BOLD, 35));
        sou.setForeground(Color.WHITE);
        add(sou);

        susBotao = new Button("Sistema Único de Saúde");
        susBotao.setBackground(new Color(0x2773FF));
        susBotao.setForeground(Color.WHITE);
        susBotao.setBorder(BorderFactory.createEmptyBorder(15, 100, 15, 100));
        susBotao.addActionListener(this);
        susBotao.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        add(susBotao);

        convenioBotao = new Button("Convênio privado de saúde");
        convenioBotao.setBackground(new Color(0x2773FF));
        convenioBotao.setForeground(Color.WHITE);
        convenioBotao.setBorder(BorderFactory.createEmptyBorder(15, 90, 15, 90));
        convenioBotao.addActionListener(this);
        convenioBotao.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        add(convenioBotao);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(boasVindas, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(sou, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(susBotao, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(convenioBotao, gbc);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == susBotao) {
            RegistroPacienteSUS registroPacienteSUS = new RegistroPacienteSUS();
            registroPacienteSUS.setVisible(true);
            this.dispose();
        } else if (e.getSource() == convenioBotao) {
            RegistroPacienteConvenio registroPacienteConvenio = new RegistroPacienteConvenio();
            registroPacienteConvenio.setVisible(true);
            this.dispose();

        }
    }
}
