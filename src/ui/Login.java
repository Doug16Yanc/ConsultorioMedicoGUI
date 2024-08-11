package ui;

import entities.Consulta;
import entities.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;


public class Login extends JFrame implements ActionListener {

    JButton login, cancel;
    JTextField tfusername, tfpassword;
    private List<Paciente> pacientes;
    private List<Consulta> consultas;

    public Login(List<Consulta> consultas) {
        this.consultas = consultas;

        getContentPane().setBackground(new Color(0x63FFF2));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        this.pacientes = new ArrayList<>();
        this.pacientes.add(new Paciente(1, "Elena Toledo", "elenatoledo@gmail.com", "(51) 5453-4562","elena", "123"));
        this.pacientes.add(new Paciente(2, "Oscar Bastos", "oscarbastos@gmail.com", "(49) 4933-6759","oscar", "145"));
        this.pacientes.add(new Paciente(3, "Edite Castro", "editecastro@gmail.com", "(50) 5098-6242", "edite","987"));

        JLabel lblusername = new JLabel("Login");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblusername, gbc);

        tfusername = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(tfusername, gbc);

        JLabel lblpassword = new JLabel("Senha");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblpassword, gbc);

        tfpassword = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(tfpassword, gbc);

        login = new JButton("Entrar");
        login.setBackground(Color.GREEN);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        login.setFont(new Font("Tahoma", Font.BOLD, 17));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(login, gbc);


        cancel = new JButton("Cancelar");
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 17));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(cancel, gbc);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/health.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(image, gbc);

        setSize(600, 300);
        setLocation(500, 250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String username = tfusername.getText();
            String password = new String(tfpassword.getText());
            Paciente pacienteFound = null;

            for (Paciente paciente : pacientes) {
                if (paciente.login.equalsIgnoreCase(username) && paciente.senha.equalsIgnoreCase(password)) {
                    pacienteFound = paciente;
                    break;
                }
            }

            if (pacienteFound != null) {
                this.dispose();
                new Consultorio(pacienteFound, consultas);
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso, seja bem-vindo(a), " + pacienteFound.nome + "!");
            } else {
                JOptionPane.showMessageDialog(this, "Credenciais inválidas!");
            }
        }
        else {
            System.exit(0);
        }
    }
}
