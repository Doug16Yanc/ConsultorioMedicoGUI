package ui;

import entities.Consulta;
import entities.Medico;
import repository.ConsultaRepository;
import utilities.ComponentsFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import static utilities.Fonts.JET_BRAINS_MONO;

public class ConsultorioMedico extends JFrame implements ActionListener {
    private JTextArea outputArea;
    private JButton realizarConsulta, btnDispensar;
    private JComboBox<String> pacientesComboBox;
    private List<Consulta> consultas;
    private final ComponentsFormat componentsFormat = new ComponentsFormat();
    private final ConsultaRepository consultaRepository = new ConsultaRepository();

    public ConsultorioMedico(Medico medico) throws SQLException {
        this.consultas = consultaRepository.buscarConsultasPorMedico(medico.getId());

        setTitle("Consultório médico");
        setSize(800, 600);
        setLocationRelativeTo(null);

        realizarConsulta = new Button("Realizar consulta");
        realizarConsulta.setBackground(new Color(0x10C100));
        realizarConsulta.addActionListener(this);
        realizarConsulta.setForeground(Color.WHITE);
        realizarConsulta.setBorder(BorderFactory.createEmptyBorder(17, 65, 17, 65));
        realizarConsulta.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 15));
        add(realizarConsulta);
        realizarConsulta.setActionCommand("Consultas : ");

        pacientesComboBox = new JComboBox<>();
        pacientesComboBox.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));

        pacientesComboBox.removeAllItems();
        if (consultas.isEmpty()) {
            pacientesComboBox.addItem("Nenhuma consulta pendente.");
        } else {
            for (Consulta c : consultas) {
                if (!c.getStatus()) {
                    pacientesComboBox.addItem(c.getPaciente().getNome() + " - " + c.getMotivo() + " - " + c.getAgora());
                }
            }
        }

        JLabel medicoLabel = new JLabel("Selecione a consulta:");
        componentsFormat.formatLabel(medicoLabel, new JPanel());
        medicoLabel.setForeground(Color.BLACK);

        btnDispensar = new Button("Dispensar a consulta");
        btnDispensar.setBackground(new Color(0xFF001A));

        btnDispensar.setForeground(Color.WHITE);
        btnDispensar.setBorder(BorderFactory.createEmptyBorder(17, 65, 17, 65));
        btnDispensar.addActionListener(this);
        btnDispensar.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 15));
        add(btnDispensar);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JPanel camposPanel = new JPanel();
        camposPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 1;
        camposPanel.add(medicoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        camposPanel.add(pacientesComboBox, gbc);

        gbc.gridy = 0;
        gbc.weighty = 1.0;
        camposPanel.add(Box.createVerticalStrut(10), gbc);

        JPanel botoesPanel = new JPanel();
        botoesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botoesPanel.add(realizarConsulta);
        botoesPanel.add(btnDispensar);

        setLayout(new BorderLayout());
        add(camposPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == realizarConsulta) {
            String selectedItem = (String) pacientesComboBox.getSelectedItem();
            if (selectedItem != null && !selectedItem.equals("Nenhuma consulta pendente.")) {
                try {
                    String[] partes = selectedItem.split(" - ");
                    String motivo = partes[1];
                    Consulta consulta = consultas.stream()
                            .filter(c -> c.getMotivo().equals(motivo) && !c.getStatus())
                            .findFirst()
                            .orElse(null);

                    if (consulta != null) {
                        consultaRepository.realizarConsulta(consulta);
                        outputArea.append("Consulta realizada com sucesso para: " + consulta.getPaciente().getNome() + "\n");

                        consultas.remove(consulta);
                        pacientesComboBox.removeItem(selectedItem);
                    } else {
                        outputArea.append("Erro ao realizar a consulta.\n");
                    }
                } catch (SQLException ex) {
                    outputArea.append("Erro ao concluir a consulta: " + ex.getMessage() + "\n");
                }
            } else {
                outputArea.append("Por favor, selecione uma consulta válida.\n");
            }
        } else if (e.getSource() == btnDispensar) {
            String selectedItem = (String) pacientesComboBox.getSelectedItem();
            if (selectedItem != null && !selectedItem.equals("Nenhuma consulta pendente.")) {
                String[] partes = selectedItem.split(" - ");
                String motivo = partes[1];
                Consulta consulta = consultas.stream()
                        .filter(c -> c.getMotivo().equals(motivo) && !c.getStatus())
                        .findFirst()
                        .orElse(null);

                if (consulta != null) {
                    outputArea.append("Consulta dispensada para: " + consulta.getPaciente().getNome() + "\n");

                    consultas.remove(consulta);
                    pacientesComboBox.removeItem(selectedItem);
                } else {
                    outputArea.append("Erro ao dispensar a consulta.\n");
                }
            } else {
                outputArea.append("Por favor, selecione uma consulta válida.\n");
            }
        }
    }
}

