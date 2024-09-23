package ui;

import entities.Consulta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static utilities.Fonts.JET_BRAINS_MONO;

public class RegistroPaciente extends JFrame implements ActionListener {
    JLabel titulo, lblNomePaciente, lblEmail, lblTelefonePaciente, lblSenhaPaciente;
    JTextField nomePaciente, emailPaciente, telefonePaciente, senhaPaciente;
    JButton btnEntrar, btnCancelar;

    public RegistroPaciente() {
        JPanel panel = new BackgroundImagePanel("src/images/medico.png");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,0, 0,  0);

        titulo = new JLabel("Registro de Paciente");
        titulo.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.BOLD, 25));
        titulo.setPreferredSize(new Dimension(400, 40));
        titulo.setLayout(new FlowLayout(FlowLayout.CENTER));

        titulo.setForeground(Color.WHITE);

        add(titulo);

        lblNomePaciente = new JLabel("Nome");
        formatLabel(lblNomePaciente);
        nomePaciente = new InputField();
        formatTextField(nomePaciente);
        lblEmail = new JLabel("Email");
        formatLabel(lblEmail);
        emailPaciente = new InputField();
        formatTextField(emailPaciente);
        lblTelefonePaciente = new JLabel("Telefone");
        formatLabel(lblTelefonePaciente);
        telefonePaciente = new InputField();
        formatTextField(telefonePaciente);
        lblSenhaPaciente = new JLabel("Senha");
        formatLabel(lblSenhaPaciente);
        senhaPaciente = new InputField();
        formatTextField(senhaPaciente);

        btnEntrar = new Button("Entrar");
        btnEntrar.setBackground(new Color(0x2773FF));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setBorder(BorderFactory.createEmptyBorder(20,100,20,100));
        btnEntrar.addActionListener(this);
        btnEntrar.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        btnEntrar.setPreferredSize(new Dimension(250, 60));
        btnEntrar.setLayout(new FlowLayout(FlowLayout.LEFT));

        add(btnEntrar);
        btnCancelar = new Button("Cancelar");
        btnCancelar.setBackground(new Color(0x2FF001A));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBorder(BorderFactory.createEmptyBorder(20,100,20,100));
        btnCancelar.addActionListener(this);
        btnCancelar.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        btnCancelar.setPreferredSize(new Dimension(250, 60));
        add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(Box.createRigidArea(new Dimension(0, 50)), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblNomePaciente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(nomePaciente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(emailPaciente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(lblTelefonePaciente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(telefonePaciente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(lblSenhaPaciente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(senhaPaciente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnEntrar);
        buttonPanel.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        add(panel, BorderLayout.CENTER);

        setSize(1920, 1080);
        setLocation(500, 250);
        setVisible(true);
    }

    void formatLabel(JLabel lbl) {
        lbl.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.BOLD, 20));
        lbl.setForeground(Color.WHITE);
        lbl.setLayout(new FlowLayout(FlowLayout.LEFT));
        lbl.setPreferredSize(new Dimension(500, 50));
        add(lbl);
    }

    void formatTextField(JTextField tf) {
        tf.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        tf.setForeground(Color.BLACK);
        tf.setPreferredSize(new Dimension(500, 50));
        add(tf);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnEntrar) {

        } else if(e.getSource() == btnCancelar) {
            this.dispose();
        }
    }
}
