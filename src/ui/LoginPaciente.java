package ui;

import entities.Consulta;
import entities.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginPaciente extends JFrame implements ActionListener {
    private Paciente paciente;
    private List<Consulta> consultas;

    public LoginPaciente(List<Consulta> consultas) {
        this.paciente = paciente;
        this.consultas = consultas;

        setTitle("Login do paciente");
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
