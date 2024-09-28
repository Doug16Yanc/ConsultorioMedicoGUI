package ui;

import entities.Consulta;
import entities.Medico;
import repository.ConsultaRepository;
import ui.components.Button;
import utilities.ComponentsFormat;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        realizarConsulta = new ui.components.Button("Realizar consulta");
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

    // Método para adicionar mensagem ao outputArea com estilo personalizado.
    private void adicionarMensagemOutputArea(String mensagem, Color cor) {
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setForeground(attributes, cor); // Define a cor do texto.
        StyleConstants.setFontFamily(attributes, JET_BRAINS_MONO.getFontName());
        StyleConstants.setFontSize(attributes, 14);

        try {
            // Adiciona a mensagem ao documento com o estilo configurado.
            Document doc = outputArea.getDocument();
            doc.insertString(doc.getLength(), getTimestamp() + " " + mensagem + "\n", attributes);

            // Faz o scroll automático para a última mensagem adicionada.
            outputArea.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para gerar um timestamp formatado.
    private String getTimestamp() {
        return "[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]";
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
                        adicionarMensagemOutputArea("Consulta realizada com sucesso para: " + consulta.getPaciente().getNome(), new Color(0, 128, 0)); // Mensagem em verde.

                        consultas.remove(consulta);
                        pacientesComboBox.removeItem(selectedItem);
                    } else {
                        adicionarMensagemOutputArea("Erro ao realizar a consulta.", Color.RED); // Mensagem em vermelho.
                    }
                } catch (SQLException ex) {
                    adicionarMensagemOutputArea("Erro ao concluir a consulta: " + ex.getMessage(), Color.RED);
                }
            } else {
                adicionarMensagemOutputArea("Por favor, selecione uma consulta válida.", Color.ORANGE); // Mensagem em laranja.
            }
        } else if (e.getSource() == btnDispensar) {
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
                        // Remove a consulta do banco de dados.
                        consultaRepository.removerConsulta(consulta);

                        // Atualiza a interface e adiciona mensagem.
                        adicionarMensagemOutputArea("Consulta dispensada para: " + consulta.getPaciente().getNome(), new Color(255, 69, 0)); // Mensagem em laranja.

                        consultas.remove(consulta);
                        pacientesComboBox.removeItem(selectedItem);
                    } else {
                        adicionarMensagemOutputArea("Erro ao dispensar a consulta.", Color.RED); // Mensagem em vermelho.
                    }
                } catch (SQLException ex) {
                    adicionarMensagemOutputArea("Erro ao dispensar a consulta: " + ex.getMessage(), Color.RED);
                }
            } else {
                adicionarMensagemOutputArea("Por favor, selecione uma consulta válida.", Color.ORANGE);
            }
        }
    }
}

