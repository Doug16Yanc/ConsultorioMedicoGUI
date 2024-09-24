import javax.swing.*;

import entities.Consulta;
import ui.Login;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Consulta> consultas = new ArrayList<>();
        SwingUtilities.invokeLater(() -> new Login(consultas));
    }
}
