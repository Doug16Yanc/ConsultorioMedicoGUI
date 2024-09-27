import javax.swing.*;

import repository.PopularMedicos;
import repository.PopularPacientes;
import ui.Login;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
        PopularPacientes.popularPacientes();
        PopularMedicos.popularMedicos();
    }
}
