package ui;

import entities.Consulta;
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
import java.util.UUID;

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
            medicoComboBox.addItem(medico.nome + " - " + medico.especialidade + " - " + medico.CRM);
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
        medicos.add(new Medico(123, "Dra. Denise Yanasse", "123456789-87", "Clínica geral", "Feminino"));
        medicos.add(new Medico(987, "Dr. Douglas Calderoni", "987654321-78", "Ortopedista", "Masculino"));
        medicos.add(new Medico(456, "Dr. Alceu Scanavini", "876544321-12", "Psiquiatra", "Masculino"));
        medicos.add(new Medico(189, "Dra. Laura Arantes", "998765432-89", "Cardiologista", "Feminino"));
        medicos.add(new Medico(834, "Dr. Thales Dalessandro", "912345678-45", "Hematologista", "Masculino"));
        return medicos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Marcar Consulta : ")) {
            marcaConsulta(paciente, consultas);
        }
    }

    public void marcaConsulta(Paciente paciente, List<Consulta> consultas) {
        UUID id = UUID.randomUUID();
        String motivo = motivoField.getText().trim();

        if (motivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, cite pelo menos um sintoma que o traz aqui, isso " +
                    "pode agilizar o trabalho do seu médico.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String agora = LocalDateTime.now().format(formatter);

        Medico medicoSelecionado = medicos.get(medicoComboBox.getSelectedIndex());
        Consulta consulta = new Consulta(id, motivo, agora, paciente, medicoSelecionado);

        consultas.add(consulta);

        oficializaConsultaComMedico(paciente, consulta, medicoSelecionado);

    }


    private String oficializaConsultaComMedico(Paciente paciente, Consulta consulta, Medico medico) {
        String message = "\tCOMPROVANTE DE CONSULTA.\n\n" +
                "Código da consulta : " + consulta.id + "\n" +
                "Data e hora da consulta : " + consulta.agora + "\n" +
                "Identificador do(a) paciente : " + paciente.id + "\n" +
                "Nome do(a) paciente : " + paciente.nome + "\n" +
                "Paciente relata : " + consulta.motivo + "\n" +
                "Identificador do(a) médico(a) : " + medico.id + "\n" +
                "Nome do(a) médico(a) : " + medico.nome;
        outputArea.setText(message);
        outputArea.setFont(new Font("Tahoma", Font.PLAIN, 20));
        return message;
    }
}
