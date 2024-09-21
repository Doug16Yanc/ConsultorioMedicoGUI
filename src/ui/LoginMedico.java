package ui;

import entities.Consulta;
import entities.Medico;
import entities.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginMedico extends JFrame implements ActionListener {
    private Medico medico;
    private List<Consulta> consultas;

    public LoginMedico(List<Consulta> consultas) {
        this.medico = medico;
        this.consultas = consultas;

        setTitle("Login do médico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 750);
        setLocationRelativeTo(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/medico.png"));
        Image i2 = i1.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
