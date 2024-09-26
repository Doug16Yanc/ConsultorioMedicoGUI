package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InputField extends JPasswordField {

    public InputField(boolean isPassword) {
        super();
        setOpaque(false);
        setBorder(new EmptyBorder(0, 10, 0, 10));

        if (!isPassword) {
            setEchoChar((char) 0);
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
        if (border instanceof EmptyBorder) {
            super.setBorder(border);
        }
    }
}