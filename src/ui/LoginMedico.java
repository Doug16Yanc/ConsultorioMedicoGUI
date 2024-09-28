package ui;

import entities.Medico;
import repository.MedicoRepository;
import ui.components.BackgroundImagePanel;
import ui.components.Button;
import ui.components.InputField;
import utilities.ComponentsFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static utilities.Fonts.JET_BRAINS_MONO;

public class LoginMedico extends JFrame implements ActionListener {
    private JButton btnEntrar, btnCancelar, btnRegistrar;
    private JLabel boasVindas, aviso, titulo, lblOu;
    private JTextField crm, senha;
    private final ComponentsFormat componentsFormat = new ComponentsFormat();

    public LoginMedico() {
        setTitle("Login do médico");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        JPanel panel = new BackgroundImagePanel("src/images/medico.png");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);

        titulo = new JLabel("Login do médico");
        titulo.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.BOLD, 35));
        titulo.setForeground(Color.WHITE);
        titulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        titulo.setPreferredSize(new Dimension(400, 40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(Box.createRigidArea(new Dimension(0, 60)), gbc);

        boasVindas = new JLabel("Bem-vindo(a), caríssimo(a) médico(a).");
        componentsFormat.formatLabel(boasVindas, panel);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(boasVindas, gbc);

        aviso = new JLabel("Por favor, use seu CRM e senha.");
        componentsFormat.formatLabel(aviso, panel);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(aviso, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(Box.createRigidArea(new Dimension(0, 30)), gbc);

        JLabel lbCrm = new JLabel("CRM");
        componentsFormat.formatLabel(lbCrm, panel);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(lbCrm, gbc);
        crm = new InputField(false);
        componentsFormat.formatTextField(crm, panel);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(crm, gbc);

        crm.requestFocusInWindow();

        JLabel lblSenha = new JLabel("Senha");
        componentsFormat.formatLabel(lblSenha, panel);
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(lblSenha, gbc);

        senha = new InputField(true);
        componentsFormat.formatTextField(senha, panel);
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(senha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnEntrar = new ui.components.Button("Entrar");
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
        panel.add(btnCancelar, gbc);

        buttonPanel.setOpaque(false);
        buttonPanel.add(btnEntrar);
        buttonPanel.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        lblOu = new JLabel("Ou");
        componentsFormat.formatLabel(lblOu, panel);
        lblOu.setPreferredSize(new Dimension(40, 60));
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 11;
        panel.add(lblOu, gbc);

        btnRegistrar = new JButton("Registrar-se");
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.BOLD, 17));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setPreferredSize(new Dimension(160, 60));
        btnRegistrar.addActionListener(this);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 12;
        panel.add(btnRegistrar, gbc);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEntrar) {
            if(senha.getText().trim().isEmpty() || crm.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            MedicoRepository medicoRepository = new MedicoRepository();

            try {
                Medico m = medicoRepository.buscarMedico(crm.getText(), senha.getText());

                if (m != null) {
                    this.dispose();

                    MenuMedico menuMedico = new MenuMedico(m, medicoRepository.pegarConsultasPorMedico(m));
                    menuMedico.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "CRM ou senha incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == btnCancelar) {
            this.dispose();
            Login login = new Login();
            login.setVisible(true);
        } else if (e.getSource() == btnRegistrar) {
            this.dispose();
            RegistroMedico registroMedico = new RegistroMedico();
            registroMedico.setVisible(true);
        }
    }
}
