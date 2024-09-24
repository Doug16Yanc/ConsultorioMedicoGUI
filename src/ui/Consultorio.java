package ui;

import entities.Consulta;
import entities.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class Consultorio extends JFrame implements ActionListener {

    private JLabel icon;
    private Paciente paciente;
    private List<Consulta> consultas;

    Consultorio(Paciente paciente, List<Consulta> consultas) {
        this.paciente = paciente;
        this.consultas = consultas;

        setTitle("Marcar consulta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        JPanel panel = new BackgroundImagePanel("src/images/medico.png");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        ImageIcon imageIcon = new ImageIcon("src/icons/iconPatient.png");
        icon = new JLabel(imageIcon);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(icon, gbc);

        /*
        JMenuItem consultaMarcar = new JMenuItem("Marcar consulta : ");
        consultaMarcar.setBackground(new Color(0x63FFF2));
        consultaMarcar.addActionListener(this);
        opcoes.add(consultaMarcar);

        JMenuItem consultasPorPaciente = new JMenuItem("Listar consultas por paciente : ");
        consultasPorPaciente.setBackground(new Color(0x63FFF2));
        consultasPorPaciente.addActionListener(this);
        opcoes.add(consultasPorPaciente);

        JMenuItem consultasPorMedico = new JMenuItem("Listar consultas por médico : ");
        consultasPorMedico.setBackground(new Color(0x63FFF2));
        consultasPorMedico.addActionListener(this);
        opcoes.add(consultasPorMedico);

        JMenuItem sair = new JMenuItem("Sair");
        sair.addActionListener(this);
        sair.setForeground(Color.RED);
        sair.setFont(new Font("sans serif", Font.BOLD, 16));
        opcoes.add(sair);

        setJMenuBar(mb);
        setVisible(true); */
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Marcar consulta : " -> {
                new Acoes(paciente, consultas);
            }
            case "Listar consultas por paciente : " -> {listarConsultasPorPaciente(consultas);}
            case "Listar consultas por médico : " -> {listarConsultasPorMedico(consultas);}
            case "Sair" -> {
                this.dispose();
                new Login(consultas);
            }
        }
    }

    public void listarConsultasPorPaciente(List<Consulta> consultas) {
        if (!consultas.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Consulta consulta : consultas) {
                sb.append(mostraConsultaPaciente(paciente, consulta)).append("\n\n");
            }
            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
            JOptionPane.showMessageDialog(null, scrollPane, "Consultas por paciente", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma consulta marcada ainda.", "Consultas por paciente", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void listarConsultasPorMedico(List<Consulta> consultas) {
        if (!consultas.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Consulta consulta : consultas) {
                sb.append(mostraConsultaMedico(consulta)).append("\n\n");
            }
            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));
            JOptionPane.showMessageDialog(null, scrollPane, "Consultas por médico", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma consulta marcada ainda.", "Consultas por médico", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    String mostraConsultaPaciente(Paciente paciente, Consulta consulta) {
        return "********************************************************\n" +
                "Identificador do(a) paciente : " + consulta.getPaciente().getId() + "\n" +
                "Nome do(a) paciente : " + consulta.getPaciente().getNome() + "\n" +
                "Email : " + consulta.getPaciente().getEmail() + "\n" +
                "Telefone : " + consulta.getPaciente().getTelefone() + "\n" +
                "Identificador da consulta : " + consulta.getId() + "\n" +
                "Motivo da consulta : " + consulta.getMotivo() + "\n" +
                "Data e hora da consulta : " + consulta.getAgora() + "\n" +
                "********************************************************\n";
    }


    String mostraConsultaMedico(Consulta consulta) {
        return "********************************************************\n" +
                "Identificador do(a) médico : " + consulta.getMedico().getId() + "\n" +
                "Nome do(a) médico(a) : " + consulta.getMedico().getNome() + "\n" +
                "CRM : " + consulta.getMedico().getCRM() + "\n" +
                "Especialidade : " + consulta.getMedico().getEspecialidade() + "\n" +
                "Identificador da consulta : " + consulta.getId() + "\n" +
                "Motivo da consulta : " + consulta.getMotivo() + "\n" +
                "Data e hora da consulta : " + consulta.getAgora() + "\n" +
                "********************************************************\n";
    }

}
