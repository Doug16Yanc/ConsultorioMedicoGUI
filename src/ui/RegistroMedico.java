package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static utilities.Fonts.JET_BRAINS_MONO;

public class RegistroMedico extends JFrame implements ActionListener {
    JLabel titulo, lblNomeMedico, lblEmailMedico, lblTelefoneMedico, lblSenhaMedico, lblCrm, lblEspecialidade;
    JTextField nomeMedico, emailMedico, telefoneMedico, senhaMedico, crm, especialidade;
    JButton btnEntrar, btnCancelar;

    public RegistroMedico() {
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

        lblNomeMedico = new JLabel("Nome");
        formatLabel(lblNomeMedico);
        nomeMedico = new InputField();
        formatTextField(nomeMedico);
        lblEmailMedico = new JLabel("Email");
        formatLabel(lblEmailMedico);
        emailMedico = new InputField();
        formatTextField(emailMedico);
        lblTelefoneMedico = new JLabel("Telefone");
        formatLabel(lblTelefoneMedico);
        telefoneMedico = new InputField();
        formatTextField(telefoneMedico);
        lblSenhaMedico = new JLabel("Senha");
        formatLabel(lblSenhaMedico);
        senhaMedico = new InputField();
        formatTextField(senhaMedico);
        lblCrm = new JLabel("CRM");
        formatLabel(lblCrm);
        lblCrm.setPreferredSize(new Dimension(250, 50));

        crm = new InputField();
        formatTextField(crm);
        crm.setPreferredSize(new Dimension(250, 50));

        lblEspecialidade = new JLabel("Especialidade");
        formatLabel(lblEspecialidade);
        lblEspecialidade.setPreferredSize(new Dimension(250, 50));

        especialidade = new InputField();
        formatTextField(especialidade);
        especialidade.setPreferredSize(new Dimension(250, 50));


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
