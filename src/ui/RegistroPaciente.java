package ui;

import entities.Paciente;
import repository.PacienteRepository;
import ui.components.BackgroundImagePanel;
import ui.components.Button;
import ui.components.InputField;
import utilities.ComponentsFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static utilities.Fonts.JET_BRAINS_MONO;

public class RegistroPaciente extends JFrame implements ActionListener {
    JLabel titulo, lblNomePaciente, lblEmail, lblTelefonePaciente, lblCodigo, lblSenhaPaciente;
    JTextField nomePaciente, emailPaciente, telefonePaciente, senhaPaciente;
    JButton btnCadastrar, btnCancelar;
    private final ComponentsFormat componentsFormat = new ComponentsFormat();

    public RegistroPaciente() {
        JPanel panel = new BackgroundImagePanel("src/images/medico.png");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);

        titulo = new JLabel("Registro de Paciente");
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
        lblSenhaPaciente = new JLabel("Senha");
        componentsFormat.formatLabel(lblSenhaPaciente, panel);
        senhaPaciente = new InputField(true);
        componentsFormat.formatTextField(senhaPaciente, panel);

        btnCadastrar = new ui.components.Button("Cadastrar");
        btnCadastrar.setBackground(new Color(0x2773FF));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        btnCadastrar.addActionListener(this);
        btnCadastrar.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        btnCadastrar.setPreferredSize(new Dimension(250, 60));
        btnCadastrar.setLayout(new FlowLayout(FlowLayout.LEFT));

        add(btnCadastrar);
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

        nomePaciente.requestFocusInWindow();

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
        buttonPanel.add(btnCadastrar);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCadastrar) {
            String nome = nomePaciente.getText();
            String email = emailPaciente.getText();
            String telefone = telefonePaciente.getText();
            String senha = senhaPaciente.getText();
            int codigo = new Random().nextInt(1000);

            if (nome.trim().isEmpty() || email.trim().isEmpty() || telefone.trim().isEmpty() || senha.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Paciente paciente = new Paciente(nome, email, telefone, senha, codigo);

            try {
                PacienteRepository pacienteRepository = new PacienteRepository();
                pacienteRepository.cadastrarPaciente(paciente);
                JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso! Vocẽ será redirecionado para página de login!");
                this.dispose();
                LoginPaciente loginPaciente = new LoginPaciente();
                loginPaciente.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar paciente: " + ex.getMessage());
            }

        } else if (e.getSource() == btnCancelar) {
            this.dispose();
            LoginPaciente loginPaciente = new LoginPaciente();
            loginPaciente.setVisible(true);
        }
    }
}
