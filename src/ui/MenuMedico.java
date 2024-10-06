package ui;

import entities.Consulta;
import entities.Medico;
import ui.components.BackgroundImagePanel;
import ui.components.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import static utilities.Fonts.JET_BRAINS_MONO;

public class MenuMedico extends JFrame implements ActionListener {
    private JButton verConsultorio, verConsultas, btnSair;
    private JLabel icon;
    private Medico medico;
    private List<Consulta> consultas;

    public MenuMedico(Medico medico, List<Consulta> consultas) {
        this.medico = medico;
        this.consultas = consultas;

        setTitle("Área do médico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        JPanel panel = new BackgroundImagePanel("src/images/medical.png");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);

        ImageIcon imageIcon = new ImageIcon("src/icons/medicos.png");
        icon = new JLabel(imageIcon);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(icon, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(Box.createRigidArea(new Dimension(0, 50)), gbc);

        verConsultorio = new ui.components.Button("Ver consultório");
        verConsultorio.setBackground(new Color(0x2773FF));
        verConsultorio.setPreferredSize(new Dimension(400, 60));
        verConsultorio.setForeground(Color.WHITE);
        verConsultorio.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        verConsultorio.addActionListener(this);
        verConsultorio.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(verConsultorio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);

        verConsultas = new ui.components.Button("Ver histórico de consultas");
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

        JLabel labelMensagem = getjLabel(medico);
        JOptionPane.showMessageDialog(this, labelMensagem, "Médico", JOptionPane.INFORMATION_MESSAGE);
    }
    private static JLabel getjLabel(Medico medico) {

        String mensagem = """
        <html>
            <body style='margin: 0; height: 100%; display: flex; justify-content: center; align-items: center; font-family: JetBrains Mono; margin-right: 20px margin-top: 30px;'>
                <div style='text-align: center;'>
                    <h1 style='font-size: 20px; margin: 0;'>Bem-vindo(a), <b>""" + medico.getNome() + """
                    </b>!</h1>
                    <p style='font-size: 14px; color: #555; margin-top: 15px;'>Estamos felizes em tê-lo(a) de volta.</p>
                    <p style='font-size: 14px; color: #2773FF; margin-top: 30px; margin-bottom: 30px;'>Confira suas consultas e conclua outras pendentes!</p>
                </div>
            </body>
        </html>
        """;

        JLabel label = new JLabel(mensagem, JLabel.CENTER);

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == verConsultorio) {
            ConsultorioMedico consultorioMedico;
            try {
                consultorioMedico = new ConsultorioMedico(medico);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            consultorioMedico.setVisible(true);
        } else if (e.getSource() == verConsultas) {
            HistoricoDeConsultas historicoDeConsultas = new HistoricoDeConsultas(medico);
            historicoDeConsultas.setVisible(true);
        } else if (e.getSource() == btnSair) {
            this.dispose();
            Login login = new Login();
            login.setVisible(true);
        }
    }
}
