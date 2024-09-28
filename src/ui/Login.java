package ui;

import ui.components.BackgroundImagePanel;
import ui.components.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static utilities.Fonts.JET_BRAINS_MONO;

public class Login extends JFrame implements ActionListener {
    private JButton medicoBotao, pacienteBotao;
    private JLabel boasVindas, sou;

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        JPanel panel = new BackgroundImagePanel("src/images/medico.png");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        boasVindas = new JLabel("Seja bem vindo(a) ao nosso sistema!");
        boasVindas.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.BOLD, 35));
        boasVindas.setForeground(Color.WHITE);
        add(boasVindas);

        sou = new JLabel("Eu sou...");
        sou.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 30));
        sou.setForeground(Color.WHITE);
        add(sou);

        medicoBotao = new ui.components.Button("Médico");
        medicoBotao.setBackground(new Color(0x2773FF));
        medicoBotao.setForeground(Color.WHITE);
        medicoBotao.setBorder(BorderFactory.createEmptyBorder(15, 100, 15, 100));
        medicoBotao.addActionListener(this);
        medicoBotao.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        add(medicoBotao);

        pacienteBotao = new Button("Paciente");
        pacienteBotao.setBackground(new Color(0x2773FF));
        pacienteBotao.setForeground(Color.WHITE);
        pacienteBotao.setBorder(BorderFactory.createEmptyBorder(15, 90, 15, 90));
        pacienteBotao.addActionListener(this);
        pacienteBotao.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        add(pacienteBotao);

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
        panel.add(medicoBotao, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(pacienteBotao, gbc);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == medicoBotao) {
            this.dispose();
            LoginMedico loginMedico = new LoginMedico();
            loginMedico.setVisible(true);
        } else if (e.getSource() == pacienteBotao) {
            this.dispose();
            LoginPaciente loginPaciente = new LoginPaciente();
            loginPaciente.setVisible(true);
        }
    }
}
