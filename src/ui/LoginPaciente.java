package ui;

import entities.Consulta;
import entities.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static utilities.Fonts.JET_BRAINS_MONO;

public class LoginPaciente extends JFrame implements ActionListener {

    private Button entrar, cancelar;
    private JLabel sus, convenio;
    private JTextField login, senha;
    private Paciente paciente;
    private List<Consulta> consultas;

    public LoginPaciente(List<Consulta> consultas) {
        setTitle("Login do paciente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        JPanel panel = new BackgroundImagePanel("src/images/medico.png");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        sus = new JLabel("Pacientes vindos do SUS, o login é o número do SUS.");
        sus.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.BOLD, 30));
        sus.setForeground(Color.WHITE);
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(sus, gbc);

        convenio = new JLabel("Pacientes vindos de convênio, usar número do convênio.");
        convenio.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.BOLD, 30));
        convenio.setForeground(Color.WHITE);
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(convenio, gbc);

        JLabel lblogin = new JLabel("Login");
        lblogin.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 30));
        lblogin.setForeground(Color.BLACK);
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblogin, gbc);

        login = new JTextField(40);
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(login, gbc);

        JLabel lbsenha = new JLabel("Senha");
        lbsenha.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 30));
        lbsenha.setForeground(Color.BLACK);
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lbsenha, gbc);

        senha = new JPasswordField(40);
        gbc.gridx = 5;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(senha, gbc);

        entrar = new Button("Entrar");
        entrar.setBackground(new Color(0x10C100));
        entrar.setForeground(Color.WHITE);
        entrar.setBorder(BorderFactory.createEmptyBorder(15, 90, 15, 90));
        entrar.addActionListener(this);
        entrar.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        gbc.gridx = 5;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(entrar, gbc);

        cancelar = new Button("Cancelar");
        cancelar.setBackground(new Color(0xFF001A));
        cancelar.setForeground(Color.WHITE);
        cancelar.setBorder(BorderFactory.createEmptyBorder(15, 90, 15, 90));
        cancelar.addActionListener(this);
        cancelar.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        gbc.gridx = 5;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(cancelar, gbc);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == entrar) {

        }
        else if(e.getSource() == cancelar) {
            this.dispose();
            Login login1 = new Login(consultas);
            login1.setVisible(true);
        }
    }
}
