package passwordgen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GUI extends JFrame {

    public JTextField lengthField;
    public JButton genButton;
    public JCheckBox checkLower, checkUpper, checkSpecial, checkNum;
    public JTextField passwordField;
    private JFrame f;
    private Letter LetterGen = new Letter();

    public GUI() {
        f = new JFrame("Password Generator");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setSize(600, 600);

        URL img = getClass().getClassLoader().getResource("/res/icon.jpg");
        if (img != null) {
            ImageIcon icon = null;
            try {
                icon = new ImageIcon(ImageIO.read(img));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            f.setIconImage(icon.getImage());
            System.out.println("Icon set!");
        }

        JPanel Panel = new JPanel();
        Panel.setLayout(null);
        Panel.setBounds(0, 0, 600, 600);

        JLabel PassTitle = new JLabel("Password length:");
        PassTitle.setBounds(50, 20, 150, 30);
        Panel.add(PassTitle);

        lengthField = new JTextField(5);
        lengthField.setBounds(200, 20, 100, 30);
        Panel.add(lengthField);

        // Corrected: Assigning to instance variables instead of redeclaring
        checkLower = new JCheckBox("1. Include lower case letters(a - z)");
        checkLower.setBounds(50, 70, 300, 30);
        Panel.add(checkLower);

        checkUpper = new JCheckBox("2. Include upper case letters(A - Z)");
        checkUpper.setBounds(50, 110, 300, 30);
        Panel.add(checkUpper);

        checkSpecial = new JCheckBox("3. Include special characters(!@#$...)");
        checkSpecial.setBounds(50, 150, 300, 30);
        Panel.add(checkSpecial);

        checkNum = new JCheckBox("4. Include number digits(0-9)");
        checkNum.setBounds(50, 190, 300, 30);
        Panel.add(checkNum);

        genButton = new JButton("Generate Password");
        genButton.setBounds(50, 240, 200, 40);
        Panel.add(genButton);

        passwordField = new JTextField();
        passwordField.setEditable(false);
        passwordField.setBounds(50, 300, 400, 40);
        Panel.add(passwordField);

        genButton.addActionListener(e -> showGeneratedPass());

        f.add(Panel);
        f.setVisible(true);
    }

    private void showGeneratedPass() {
        try {
            int length = Integer.parseInt(lengthField.getText().trim());

            List<Integer> options = new ArrayList<>();
            if (checkLower.isSelected()) options.add(1);
            if (checkUpper.isSelected()) options.add(2);
            if (checkSpecial.isSelected()) options.add(3);
            if (checkNum.isSelected()) options.add(4);

            if (options.isEmpty()) {
                passwordField.setText("Select at least one option!");
                return;
            }

            int[] optionsArray = options.stream().mapToInt(i -> i).toArray();
            String generatedPassword = LetterGen.generate(optionsArray, length);
            passwordField.setText(generatedPassword);

        } catch (NumberFormatException e) {
            passwordField.setText("Enter a valid number!");
        }
    }

}
