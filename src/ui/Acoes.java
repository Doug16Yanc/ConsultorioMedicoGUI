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

public class Acoes extends JFrame implements ActionListener {

    private ArrayList<Medico> medicos;
    private JComboBox<String> medicoComboBox;
    private JTextArea outputArea;
    private JTextField motivoField;
    private final Paciente paciente;
    private final List<Consulta> consultas;

    public Acoes(Paciente paciente, List<Consulta> consultas) {
        this.paciente = paciente;
        this.consultas = consultas;

        setTitle("Consultório");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JButton marcarConsulta = new JButton("Marcar Consulta : ");
        marcarConsulta.setBackground(new Color(0x63FFF2));
        marcarConsulta.addActionListener(this);
        marcarConsulta.setActionCommand("Marcar Consulta : ");

        medicos = inicializaMedicos();

        JLabel medicoLabel = new JLabel("Selecione o médico:");
        medicoComboBox = new JComboBox<>();
        for (Medico medico : medicos) {
            medicoComboBox.addItem(medico.getNome() + " - " + medico.getEspecialidade() + " - " + medico.getCRM());
        }

        JLabel motivoLabel = new JLabel("Motivo da consulta:");
        motivoField = new JTextField(20);

        JButton sairButton = new JButton("Sair");
        sairButton.setBackground(new Color(0xFF6347));
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(medicoLabel);
        panel.add(medicoComboBox);
        panel.add(motivoLabel);
        panel.add(motivoField);
        panel.add(marcarConsulta);
        panel.add(sairButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

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
