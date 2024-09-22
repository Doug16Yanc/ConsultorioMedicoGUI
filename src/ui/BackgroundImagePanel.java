package ui;

import javax.swing.*;
import java.awt.*;

public class BackgroundImagePanel extends JPanel {
    private final Image backgroundImage;

    public BackgroundImagePanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
