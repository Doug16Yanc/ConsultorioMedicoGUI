package ui;

import entities.Consulta;
import entities.Medico;
import entities.Paciente;
import repository.ConsultaRepository;
import repository.MedicoRepository;
import utilities.ComponentsFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
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
    private final ComponentsFormat componentsFormat = new ComponentsFormat();
    private final MedicoRepository medicoRepository = new MedicoRepository();

    public MarcaConsulta(Paciente paciente, List<Consulta> consultas) throws SQLException {
        this.paciente = paciente;
        this.consultas = consultas;

        setTitle("Marcar consulta");
        setSize(800, 800);
        setLocationRelativeTo(null);

        marcarConsulta = new Button("Marcar");
        marcarConsulta.setBackground(new Color(0x10C100));
        marcarConsulta.addActionListener(this);
        marcarConsulta.setForeground(Color.WHITE);
        marcarConsulta.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        marcarConsulta.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        add(marcarConsulta);
        marcarConsulta.setActionCommand("Marcar Consulta : ");

        medicos = (ArrayList<Medico>) medicoRepository.buscarTodosOsMedicos();

        JLabel medicoLabel = new JLabel("Selecione o médico:");
        componentsFormat.formatLabel(medicoLabel, new JPanel());
        medicoLabel.setForeground(Color.BLACK);

        medicoComboBox = new JComboBox<>();
        medicoComboBox.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 17));
        for (Medico medico : medicos) {
            medicoComboBox.addItem(medico.getNome() + " - " + medico.getEspecialidade() + " - " + medico.getCRM());
        }

        JLabel motivoLabel = new JLabel("Motivo da consulta:");
        componentsFormat.formatLabel(motivoLabel, new JPanel());
        motivoLabel.setForeground(Color.BLACK);
        motivoField = new InputField(false);
        componentsFormat.formatTextField(motivoField, new JPanel());

        JPanel motivoPanel = new JPanel();
        motivoPanel.setLayout(new BorderLayout());
        motivoPanel.add(motivoLabel, BorderLayout.NORTH);
        motivoPanel.add(motivoField, BorderLayout.CENTER);

        sairButton = new Button("Voltar");
        sairButton.setBackground(new Color(0xFF001A));

        sairButton.setForeground(Color.WHITE);
        sairButton.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
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
        gbc.weightx = 1.0;
        camposPanel.add(medicoComboBox, gbc);

        gbc.gridy = 0;
        gbc.weighty = 1.0;
        camposPanel.add(Box.createVerticalStrut(10), gbc);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Marcar Consulta : ")) {
            try {
                marcaConsulta(paciente, consultas);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void marcaConsulta(Paciente paciente, List<Consulta> consultas) throws SQLException {
        String motivo = motivoField.getText().trim();

        if (motivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, cite pelo menos um sintoma que o traz aqui, isso " +
                    "pode agilizar o trabalho do seu médico.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDateTime agora = LocalDateTime.now();
        Timestamp timestampAgora = Timestamp.valueOf(agora);
        Medico medicoSelecionado = medicos.get(medicoComboBox.getSelectedIndex());

        ConsultaRepository consultaRepository = new ConsultaRepository();

        Consulta consulta = new Consulta(motivo, timestampAgora, paciente, medicoSelecionado);
        consultaRepository.adicionarConsulta(consulta);
        consultas.add(consulta);
        oficializaConsultaComMedico(paciente, consulta, medicoSelecionado);
    }

    private String oficializaConsultaComMedico(Paciente paciente, Consulta consulta, Medico medico) {
        String message = "\tCOMPROVANTE DE CONSULTA.\n\n" +
                "Código da consulta : " + consulta.getId() + "\n" +
                "Data e hora marcada : " + consulta.getAgora() + "\n" +
                "Identificador do(a) paciente : " + paciente.getId() + "\n" +
                "Nome do(a) paciente : " + paciente.getNome() + "\n" +
                "Paciente relata : " + consulta.getMotivo() + "\n" +
                "Identificador do(a) médico(a) : " + medico.getId() + "\n" +
                "Nome do(a) médico(a) : " + medico.getNome();
        outputArea.setText(message);
        outputArea.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        return message;
    }
}