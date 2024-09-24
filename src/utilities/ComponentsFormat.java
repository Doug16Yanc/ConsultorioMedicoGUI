package utilities;

import javax.swing.*;
import java.awt.*;

import static utilities.Fonts.JET_BRAINS_MONO;

public class ComponentsFormat {
    public void formatLabel(JLabel lbl, JPanel panel) {
        lbl.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.BOLD, 20));
        lbl.setForeground(Color.WHITE);
        lbl.setLayout(new FlowLayout(FlowLayout.LEFT));
        lbl.setPreferredSize(new Dimension(500, 50));
        panel.add(lbl);
    }

    public void formatTextField(JTextField tf, JPanel panel) {
        tf.setFont(new Font(JET_BRAINS_MONO.getFontName(), Font.PLAIN, 20));
        tf.setForeground(Color.BLACK);
        tf.setPreferredSize(new Dimension(500, 50));
        panel.add(tf);
    }
}
