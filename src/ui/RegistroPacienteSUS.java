package ui;

import entities.Paciente;
import repository.PacienteRepository;
import utilities.ComponentsFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import static utilities.Fonts.JET_BRAINS_MONO;

public class RegistroPacienteSUS extends JFrame implements ActionListener {
    JLabel titulo, lblNomePaciente, lblEmail, lblTelefonePaciente, lblSUS, lblSenhaPaciente;
    JTextField nomePaciente, emailPaciente, telefonePaciente, susPaciente, senhaPaciente;
    JButton btnEntrar, btnCancelar;
    private final ComponentsFormat componentsFormat = new ComponentsFormat();

    public RegistroPacienteSUS() {
        JPanel panel = new BackgroundImagePanel("src/images/medico.png");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);

        titulo = new JLabel("Registro de Paciente do SUS");
        titulo.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.BOLD, 25));
        titulo.setPreferredSize(new Dimension(400, 40));
        titulo.setLayout(new FlowLayout(FlowLayout.CENTER));

        titulo.setForeground(Color.WHITE);

        add(titulo);

        lblNomePaciente = new JLabel("Nome");
        componentsFormat.formatLabel(lblNomePaciente, panel);
        nomePaciente = new InputField(false);
        componentsFormat.formatTextField(nomePaciente, panel);
        lblEmail = new JLabel("Email");
        componentsFormat.formatLabel(lblEmail, panel);
        emailPaciente = new InputField(false);
        componentsFormat.formatTextField(emailPaciente, panel);
        lblTelefonePaciente = new JLabel("Telefone");
        componentsFormat.formatLabel(lblTelefonePaciente, panel);
        telefonePaciente = new InputField(false);
        componentsFormat.formatTextField(telefonePaciente, panel);
        lblSUS = new JLabel("Número do SUS");
        componentsFormat.formatLabel(lblSUS, panel);
        susPaciente = new InputField(false);
        componentsFormat.formatTextField(susPaciente, panel);
        lblSenhaPaciente = new JLabel("Senha");
        componentsFormat.formatLabel(lblSenhaPaciente, panel);
        senhaPaciente = new InputField(true);
        componentsFormat.formatTextField(senhaPaciente, panel);

        btnEntrar = new Button("Entrar");
        btnEntrar.setBackground(new Color(0x2773FF));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        btnEntrar.addActionListener(this);
        btnEntrar.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        btnEntrar.setPreferredSize(new Dimension(250, 60));
        btnEntrar.setLayout(new FlowLayout(FlowLayout.LEFT));

        add(btnEntrar);
        btnCancelar = new Button("Cancelar");
        btnCancelar.setBackground(new Color(0x2FF001A));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
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
        panel.add(lblSUS, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(susPaciente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(lblSenhaPaciente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        panel.add(senhaPaciente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        panel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnEntrar);
        buttonPanel.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        add(panel, BorderLayout.CENTER);

        setSize(1920, 1080);
        setLocation(500, 250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEntrar) {

            String nome = nomePaciente.getText();
            String email = emailPaciente.getText();
            String telefone = telefonePaciente.getText();
            String senha = senhaPaciente.getText();

            Paciente paciente = new Paciente(new Random().nextInt(), nome, senha, email, telefone);

            try {
                PacienteRepository pacienteRepository = new PacienteRepository();
                pacienteRepository.cadastrarPaciente(paciente);
                JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso!");
                this.dispose();
                MenuPaciente menuPaciente = new MenuPaciente(new ArrayList<>());
                menuPaciente.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar paciente: " + ex.getMessage());
            }

        } else if (e.getSource() == btnCancelar) {
            this.dispose();
            LoginPaciente loginPaciente = new LoginPaciente(new ArrayList<>());
            loginPaciente.setVisible(true);
        }
    }
}
