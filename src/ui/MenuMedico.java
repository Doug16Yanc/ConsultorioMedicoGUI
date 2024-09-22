package ui;

import entities.Consulta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static utilities.Fonts.JET_BRAINS_MONO;

public class MenuMedico extends JFrame implements ActionListener {
    private JButton verConsultorio, verConsultas;
    private JLabel icon;
    private Image backgroundImage;
    private List<Consulta> consultas;

    public MenuMedico(List<Consulta> consultas) {
        setTitle("Menu do médico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        JPanel panel = new BackgroundImagePanel("src/images/medical.png");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        ImageIcon imageIcon = new ImageIcon("src/icons/iconMedical.png");
        icon = new JLabel(imageIcon);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(icon, gbc);

        verConsultorio = new JButton("Marcar consulta");
        verConsultorio.setBackground(new Color(0x2773FF));
        verConsultorio.setForeground(Color.WHITE);
        verConsultorio.setBorder(BorderFactory.createEmptyBorder(15, 100, 15, 100));
        verConsultorio.addActionListener(this);
        verConsultorio.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(verConsultorio, gbc);

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
        if(e.getSource() == verConsultorio) {

        } else if (e.getSource() == verConsultas) {


        }
    }
}
