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
    JButton entrar, cancelar;
    JTextField login, senha;
    private Medico medico;
    private List<Consulta> consultas;

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(ImageIcon imageIcon) {
            this.backgroundImage = imageIcon.getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public LoginMedico(List<Consulta> consultas) {
        this.medico = medico;
        this.consultas = consultas;

        setTitle("Login do médico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 750);
        setLocationRelativeTo(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/medico.png"));
        Image i2 = i1.getImage().getScaledInstance(850, 750, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);

        BackgroundPanel backgroundPanel = new BackgroundPanel(i3);
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblogin = new JLabel("Login");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        backgroundPanel.add(lblogin, gbc);

        login = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        backgroundPanel.add(login, gbc);

        JLabel lbsenha = new JLabel("Senha");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        backgroundPanel.add(lbsenha, gbc);

        senha = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        backgroundPanel.add(senha, gbc);

        entrar = new JButton("Entrar");
        entrar.setBackground(Color.GREEN);
        entrar.setForeground(Color.WHITE);
        entrar.addActionListener(this);
        entrar.setFont(new Font("Tahoma", Font.BOLD, 17));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(entrar, gbc);

        cancelar = new JButton("Cancelar");
        cancelar.setBackground(Color.RED);
        cancelar.setForeground(Color.WHITE);
        cancelar.addActionListener(this);
        cancelar.setFont(new Font("Tahoma", Font.BOLD, 17));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        backgroundPanel.add(cancelar, gbc);

        setContentPane(backgroundPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}