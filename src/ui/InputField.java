package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InputField extends JPasswordField {

    public InputField(boolean isPassword) {
        super();
        setOpaque(false);
        if (!isPassword) {
            setEchoChar((char) 0); // Para mostrar caracteres normais
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        int borderRadius = 15;
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);

        super.paintComponent(g);
    }

    @Override
    public void setBorder(Border border) {
    }
}
