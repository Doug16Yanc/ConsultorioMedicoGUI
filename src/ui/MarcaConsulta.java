package ui;

import entities.Consulta;
import entities.Especialidade;
import entities.Medico;
import entities.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static utilities.Fonts.JET_BRAINS_MONO;

public class MarcaConsulta extends JFrame implements ActionListener {

    private ArrayList<Medico> medicos;
    private JComboBox<String> medicoComboBox;
    private JTextArea outputArea;
    private JTextField motivoField;
    private JButton marcarConsulta, sairButton;
    private final Paciente paciente;
    private final List<Consulta> consultas;

    public MarcaConsulta(Paciente paciente, List<Consulta> consultas) {
        this.paciente = paciente;
        this.consultas = consultas;

        setTitle("Marcar consulta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        marcarConsulta = new Button("Marcar");
        marcarConsulta.setBackground(new Color(0x10C100));
        marcarConsulta.addActionListener(this);
        marcarConsulta.setBorder(BorderFactory.createEmptyBorder(15,70,15,70));
        marcarConsulta.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        add(marcarConsulta);
        marcarConsulta.setActionCommand("Marcar Consulta : ");

        medicos = inicializaMedicos();

        JLabel medicoLabel = new JLabel("Selecione o médico:");
        medicoComboBox = new JComboBox<>();
        for (Medico medico : medicos) {
            medicoComboBox.addItem(medico.getNome() + " - " + medico.getEspecialidade() + " - " + medico.getCRM());
        }

        JLabel motivoLabel = new JLabel("Motivo da consulta:");
        motivoField = new JTextField(20);

        JPanel motivoPanel = new JPanel();
        motivoPanel.setLayout(new BorderLayout());
        motivoPanel.add(motivoLabel, BorderLayout.NORTH);
        motivoPanel.add(motivoField, BorderLayout.CENTER);

        sairButton = new Button("Voltar");
        sairButton.setBackground(new Color(0xFF001A));
        sairButton.setBorder(BorderFactory.createEmptyBorder(15,70,15,70));
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

        gbc.gridx = 0;
        gbc.gridy = 1;
        camposPanel.add(medicoComboBox, gbc);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        camposPanel.add(Box.createVerticalStrut(50), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weighty = 0;
        camposPanel.add(motivoPanel, gbc);

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


    public ArrayList<Medico> inicializaMedicos() {
        ArrayList<Medico> medicos = new ArrayList<>();
        medicos.add(new Medico(123, "Dra. Denise Yanasse", "123456789-87", "senhaDenise123", "denise@clinica.com", "(11) 98765-4321", new Especialidade("Clínica geral")));
        medicos.add(new Medico(987, "Dr. Douglas Calderoni", "987654321-78", "senhaDouglas987", "douglas@ortopedia.com", "(21) 91234-5678", new Especialidade("Ortopedista")));
        medicos.add(new Medico(456, "Dr. Alceu Scanavini", "876544321-12", "senhaAlceu456", "alceu@psiquiatra.com", "(31) 99876-5432", new Especialidade("Psiquiatra")));
        medicos.add(new Medico(189, "Dra. Laura Arantes", "998765432-89", "senhaLaura189", "laura@cardio.com", "(41) 92345-6789", new Especialidade("Cardiologista")));
        medicos.add(new Medico(834, "Dr. Thales Dalessandro", "912345678-45", "senhaThales834", "thales@hemato.com", "(51) 98765-4321", new Especialidade("Hematologista")));

        return medicos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Marcar Consulta : ")) {
            marcaConsulta(paciente, consultas);
        }
    }

    public void marcaConsulta(Paciente paciente, List<Consulta> consultas) {
        String motivo = motivoField.getText().trim();

        if (motivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, cite pelo menos um sintoma que o traz aqui, isso " +
                    "pode agilizar o trabalho do seu médico.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String agora = LocalDateTime.now().format(formatter);

        Medico medicoSelecionado = medicos.get(medicoComboBox.getSelectedIndex());
        Consulta consulta = new Consulta(motivo, agora, paciente, medicoSelecionado);

        consultas.add(consulta);

        oficializaConsultaComMedico(paciente, consulta, medicoSelecionado);

    }

    private String oficializaConsultaComMedico(Paciente paciente, Consulta consulta, Medico medico) {
        String message = "\tCOMPROVANTE DE CONSULTA.\n\n" +
                "Código da consulta : " + consulta.getId() + "\n" +
                "Data e hora da consulta : " + consulta.getAgora() + "\n" +
                "Identificador do(a) paciente : " + paciente.getId() + "\n" +
                "Nome do(a) paciente : " + paciente.getNome() + "\n" +
                "Paciente relata : " + consulta.getMotivo() + "\n" +
                "Identificador do(a) médico(a) : " + medico.getId() + "\n" +
                "Nome do(a) médico(a) : " + medico.getNome();
        outputArea.setText(message);
        outputArea.setFont(new Font("Tahoma", Font.PLAIN, 20));
        return message;
    }
}