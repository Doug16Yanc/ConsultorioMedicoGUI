import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Login extends JFrame {

    Login() {

        JFrame frame = new JFrame();
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;


        getContentPane().setBackground(new Color(0x63FFF2));
        setSize(950, 500);
        setLocation(750, 200);
        setLayout(null);

        GroundImage image = new GroundImage("icons/medical.png");
        frame.add(image);
        createLabel();
        createTextField();
        createImage();;

        setVisible(true);
    }

    private void createLabel() {
        JLabel label = new JLabel();
        JLabel nameLabel = new JLabel("Username");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        nameLabel.setBounds(40, 20, 150, 40);
        nameLabel.setForeground(Color.BLACK);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        passwordLabel.setBounds(40, 50, 150, 40);

        add(nameLabel);
        add(passwordLabel);

    }

    private void createTextField() {
        JTextField textField = new JTextField();
        textField.setBounds(170, 20, 200, 30);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setBackground(Color.WHITE);
        add(textField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(170, 50, 200, 30);
        passwordField.setFont(new Font("Arial", Font.BOLD, 20));
        passwordField.setBackground(Color.WHITE);
        add(passwordField);

    }

    private void createImage() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/health.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);

    }

    public static void main(String[] args) {
        new Login();
    }
}