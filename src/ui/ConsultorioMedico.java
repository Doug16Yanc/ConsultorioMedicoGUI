package ui;

import entities.Consulta;
import entities.Medico;
import entities.Paciente;
import utilities.ComponentsFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static utilities.Fonts.JET_BRAINS_MONO;

public class ConsultorioMedico extends JFrame implements ActionListener {
    private ArrayList<Medico> medicos;
    private JComboBox<String> medicoComboBox;
    private JTextArea outputArea;
    private JTextField motivoField;
    private JButton marcarConsulta, sairButton;
    private final Paciente paciente;
    private final List<Consulta> consultas;
    private final ComponentsFormat componentsFormat = new ComponentsFormat();


    public ConsultorioMedico(Paciente paciente, List<Consulta> consultas) {
        this.paciente = paciente;
        this.consultas = consultas;

        setTitle("Consultório médico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        marcarConsulta = new Button("Realizar consulta");
        marcarConsulta.setBackground(new Color(0x10C100));
        marcarConsulta.addActionListener(this);
        marcarConsulta.setForeground(Color.WHITE);
        marcarConsulta.setBorder(BorderFactory.createEmptyBorder(20,100,20,100));
        marcarConsulta.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        add(marcarConsulta);
        marcarConsulta.setActionCommand("Consultas : ");


        JLabel medicoLabel = new JLabel("Selecione a consulta:");
        componentsFormat.formatLabel(medicoLabel, new JPanel());
        medicoLabel.setForeground(Color.BLACK);
        /*
        medicoComboBox = new JComboBox<>();
        medicoComboBox.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 17));
        for (Medico medico : medicos) {
            medicoComboBox.addItem(medico.getNome() + " - " + medico.getEspecialidade() + " - " + medico.getCRM());
        }
        */

        sairButton = new Button("Dispensar a consulta");
        sairButton.setBackground(new Color(0xFF001A));

        sairButton.setForeground(Color.WHITE);
        sairButton.setBorder(BorderFactory.createEmptyBorder(20,100,20,100));
        sairButton.addActionListener(this);
        sairButton.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        add(sairButton);
        sairButton.addActionListener(e -> dispose());

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JPanel camposPanel = new JPanel();
        camposPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 0;
        camposPanel.add(medicoLabel, gbc);

        /*gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        camposPanel.add(medicoComboBox, gbc); */

        gbc.gridy = 0;
        gbc.weighty = 1.0;
        camposPanel.add(Box.createVerticalStrut(10), gbc);


        JPanel botoesPanel = new JPanel();
        botoesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botoesPanel.add(marcarConsulta);
        botoesPanel.add(sairButton);

        setLayout(new BorderLayout());
        add(camposPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
