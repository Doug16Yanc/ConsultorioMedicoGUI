package ui;

import entities.Especialidade;
import entities.Medico;
import repository.MedicoRepository;
import utilities.ComponentsFormat;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static utilities.Fonts.JET_BRAINS_MONO;

public class RegistroMedico extends JFrame implements ActionListener {
    JLabel titulo, lblNomeMedico, lblEmailMedico, lblTelefoneMedico, lblSenhaMedico, lblCrm, lblEspecialidade;
    JTextField nomeMedico, emailMedico, telefoneMedico, senhaMedico, crm, especialidade;
    JButton btnEntrar, btnCancelar;
    private final ComponentsFormat componentsFormat = new ComponentsFormat();

    public RegistroMedico() {
        JPanel panel = new BackgroundImagePanel("src/images/medico.png");
        setTitle("Registro Médico");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,0, 0,  0);

        titulo = new JLabel("Registro Médico");
        titulo.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.BOLD, 35));
        titulo.setPreferredSize(new Dimension(400, 40));
        titulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        titulo.setForeground(Color.WHITE);
        add(titulo);

        lblNomeMedico = new JLabel("Nome");
        componentsFormat.formatLabel(lblNomeMedico, panel);
        nomeMedico = new InputField(false);
        componentsFormat.formatTextField(nomeMedico, panel);
        lblEmailMedico = new JLabel("Email");
        componentsFormat.formatLabel(lblEmailMedico, panel);
        emailMedico = new InputField(false);
        componentsFormat.formatTextField(emailMedico, panel);
        lblTelefoneMedico = new JLabel("Telefone");
        componentsFormat.formatLabel(lblTelefoneMedico, panel);
        telefoneMedico = new InputField(false);
        componentsFormat.formatTextField(telefoneMedico, panel);
        lblSenhaMedico = new JLabel("Senha");
        componentsFormat.formatLabel(lblSenhaMedico, panel);
        senhaMedico = new InputField(true);
        componentsFormat.formatTextField(senhaMedico, panel);
        lblCrm = new JLabel("CRM");
        componentsFormat.formatLabel(lblCrm, panel);
        lblCrm.setPreferredSize(new Dimension(250, 50));
        crm = new InputField(false);
        componentsFormat.formatTextField(crm, panel);
        crm.setPreferredSize(new Dimension(245, 50));

        lblEspecialidade = new JLabel("Especialidade");
        componentsFormat.formatLabel(lblEspecialidade, panel);
        lblEspecialidade.setPreferredSize(new Dimension(250, 50));

        especialidade = new InputField(false);
        componentsFormat.formatTextField(especialidade, panel);
        especialidade.setPreferredSize(new Dimension(245, 50));


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
        panel.add(Box.createRigidArea(new Dimension(0, 60)), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblNomeMedico, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(nomeMedico, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblEmailMedico, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(emailMedico, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(lblTelefoneMedico, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(telefoneMedico, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(lblSenhaMedico, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(senhaMedico, gbc);

        JPanel label = new JPanel(new FlowLayout(FlowLayout.LEFT, 7, 0));
        label.setOpaque(false);
        label.add(lblCrm);
        label.add(lblEspecialidade);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        panel.add(label, gbc);

        JPanel input = new JPanel(new FlowLayout(FlowLayout.LEFT, 7, 0));
        input.setOpaque(false);
        input.add(crm);
        input.add(especialidade);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        panel.add(input, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        panel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
            String nome = nomeMedico.getText();
            String email = emailMedico.getText();
            String telefone = telefoneMedico.getText();
            String senha = senhaMedico.getText();
            String CRM = crm.getText();
            String especialidadeNome = especialidade.getText();

            Medico medico = new Medico(nome, CRM, senha, email, telefone, new Especialidade(especialidadeNome));

            try {
                MedicoRepository medicoRepository = new MedicoRepository();
                medicoRepository.cadastrarMedico(medico);
                JOptionPane.showMessageDialog(this, "Médico cadastrado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar médico: " + ex.getMessage());
            }
        }else if(e.getSource() == btnCancelar) {
            this.dispose();
            LoginMedico loginMedico = new LoginMedico(new ArrayList<>());
            loginMedico.setVisible(true);
        }
    }
}
