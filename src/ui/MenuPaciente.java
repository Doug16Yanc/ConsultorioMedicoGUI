package ui;

import entities.Consulta;
import entities.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static utilities.Fonts.JET_BRAINS_MONO;

public class MenuPaciente extends JFrame implements ActionListener {
    private JButton marcarConsulta, verConsultas, btnSair;
    private Paciente paciente;
    private JLabel icon;
    private List<Consulta> consultas;

    public MenuPaciente(List<Consulta> consultas) {
        setTitle("Menu do paciente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        JPanel panel = new BackgroundImagePanel("src/images/medical.png");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);

        ImageIcon imageIcon = new ImageIcon("src/icons/iconPatient.png");
        icon = new JLabel(imageIcon);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(icon, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(Box.createRigidArea(new Dimension(0, 50)), gbc);

        marcarConsulta = new Button("Marcar consulta");
        marcarConsulta.setBackground(new Color(0x2773FF));
        marcarConsulta.setPreferredSize(new Dimension(400, 60));

        marcarConsulta.setForeground(Color.WHITE);
        marcarConsulta.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        marcarConsulta.addActionListener(this);
        marcarConsulta.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(marcarConsulta, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);

        verConsultas = new Button("Ver minhas consultas");
        verConsultas.setBackground(new Color(0x2773FF));
        verConsultas.setPreferredSize(new Dimension(400, 60));

        verConsultas.setForeground(Color.WHITE);
        verConsultas.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        verConsultas.addActionListener(this);
        verConsultas.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(verConsultas, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);

        btnSair = new Button("Sair");
        btnSair.setBackground(new Color(0x2FF001A));
        btnSair.setPreferredSize(new Dimension(400, 60));
        btnSair.setForeground(Color.WHITE);
        btnSair.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        btnSair.addActionListener(this);
        btnSair.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(btnSair, gbc);

        setContentPane(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == marcarConsulta) {
            MarcaConsulta marcaConsulta = new MarcaConsulta(paciente, consultas);
            marcaConsulta.setVisible(true);

        } else if (e.getSource() == verConsultas) {
            VerConsultas verConsultas = new VerConsultas(paciente, consultas);
            verConsultas.setVisible(true);
        } else if (e.getSource() == btnSair) {
            this.dispose();
            Login login = new Login(new ArrayList<>());
            login.setVisible(true);
        }
    }
}
