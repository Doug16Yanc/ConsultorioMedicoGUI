package ui;

import entities.Consulta;
import entities.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static utilities.Fonts.JET_BRAINS_MONO;

public class MenuPaciente extends JFrame implements ActionListener {
    private Paciente paciente;
    private JButton marcarConsulta, verConsultas;
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
        gbc.insets = new Insets(10, 10, 10, 10);

        ImageIcon imageIcon = new ImageIcon("src/icons/iconPatient.png");
        icon = new JLabel(imageIcon);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(icon, gbc);

        marcarConsulta = new JButton("Marcar consulta");
        marcarConsulta.setBackground(new Color(0x2773FF));
        marcarConsulta.setForeground(Color.WHITE);
        marcarConsulta.setBorder(BorderFactory.createEmptyBorder(15, 100, 15, 100));
        marcarConsulta.addActionListener(this);
        marcarConsulta.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(marcarConsulta, gbc);

        verConsultas = new JButton("Ver minhas consultas");
        verConsultas.setBackground(new Color(0x2773FF));
        verConsultas.setForeground(Color.WHITE);
        verConsultas.setBorder(BorderFactory.createEmptyBorder(15, 90, 15, 90));
        verConsultas.addActionListener(this);
        verConsultas.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(verConsultas, gbc);

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
        }
    }
}
