import javax.swing.*;

import entities.Consulta;
import repository.PopularMedicos;
import repository.PopularPacientes;
import ui.Login;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
        PopularPacientes.popularPacientes();
        PopularMedicos.popularMedicos();
    }
}
