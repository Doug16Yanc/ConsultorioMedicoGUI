package ui;

import entities.Consulta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import static utilities.Fonts.JET_BRAINS_MONO;

public class Login extends JFrame implements ActionListener {
    private JButton medicoBotao, pacienteBotao;
    private JLabel boasVindas, sou;
    private Image backgroundImage;
    private Paciente paciente;
    private List<Consulta> consultas;


    public Login(List<Consulta> consultas) {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        JPanel panel = new BackgroundImagePanel("src/images/medico.png");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10, 10, 10);

        setVisible(true);


        sou = new JLabel("Eu sou");
        sou.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 30));
        sou.setForeground(Color.WHITE);
        add(sou);

        boasVindas = new JLabel("Seja bem vindo(a) ao nosso sistema!");
        boasVindas.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 30));
        boasVindas.setForeground(Color.WHITE);
        add(boasVindas);

        medicoBotao = new Button("Médico");
        medicoBotao.setBackground(new Color(0x2773FF));
        medicoBotao.setForeground(Color.WHITE);
        medicoBotao.setBorder(BorderFactory.createEmptyBorder(15,100,15,100));
        medicoBotao.addActionListener(this);
        medicoBotao.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        add(medicoBotao);

        pacienteBotao = new Button("Paciente");
        pacienteBotao.setBackground(new Color(0x2773FF));
        pacienteBotao.setForeground(Color.WHITE);
        pacienteBotao.setBorder(BorderFactory.createEmptyBorder(15,90,15,90));
        pacienteBotao.addActionListener(this);
        pacienteBotao.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        add(pacienteBotao);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(boasVindas, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(sou, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(Box.createRigidArea(new Dimension(0, 20)), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(medicoBotao, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(pacienteBotao, gbc);


        add(panel, BorderLayout.CENTER);

        setSize(800, 600);
        setLocation(500, 250);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == medicoBotao) {
            LoginMedico loginMedico = new LoginMedico(consultas);
            loginMedico.setVisible(true);
            this.dispose();
        } else if (e.getSource() == pacienteBotao) {
            LoginPaciente loginPaciente = new LoginPaciente(consultas);
            loginPaciente.setVisible(true);
            this.dispose();

        }
    }
}

/*
public class Login extends JFrame implements ActionListener {

    JButton medico, paciente;

    private List<Paciente> pacientes;
    private List<Consulta> consultas;

    public Login(List<Consulta> consultas) {
        this.consultas = consultas;

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(0x63FFF2));
        setSize(850, 750);
        setLocationRelativeTo(null);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        this.pacientes = new ArrayList<>();
        this.pacientes.add(new Paciente(1, "Elena Toledo", "elenatoledo@gmail.com", "(51) 5453-4562","elena", "123"));
        this.pacientes.add(new Paciente(2, "Oscar Bastos", "oscarbastos@gmail.com", "(49) 4933-6759","oscar", "145"));
        this.pacientes.add(new Paciente(3, "Edite Castro", "editecastro@gmail.com", "(50) 5098-6242", "edite","987"));


        medico = new JButton("Médico");
        medico.setBackground(Color.GREEN);
        medico.setForeground(Color.WHITE);
        medico.addActionListener(this);
        medico.setFont(new Font("Tahoma", Font.BOLD, 17));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(medico, gbc);

        paciente = new JButton("Paciente");
        paciente.setBackground(Color.RED);
        paciente.setForeground(Color.WHITE);
        paciente.addActionListener(this);
        paciente.setFont(new Font("Tahoma", Font.BOLD, 17));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(paciente, gbc);

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
        if (e.getSource() == medico) {
        }
        else if (e.getSource() == paciente) {

        }
    }
}
 */