package cal.myproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Calculator {
    private JFrame frame;
    private JTextField textField;
    private double num1, num2, result;
    private char operator;

    public Calculator() {
        frame = new JFrame("電卓");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        panel.setBackground(new Color(255, 230, 240)); // パステルピンク背景
        frame.add(panel, BorderLayout.CENTER);

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setBackground(new Color(200, 230, 255)); // パステルブルー
            button.setForeground(Color.DARK_GRAY);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                textField.setText(textField.getText() + command);
            } else if (command.equals("C")) {
                textField.setText("");
                num1 = num2 = result = 0;
                operator = ' ';
            } else if (command.equals("=")) {
                num2 = Double.parseDouble(textField.getText());
                result = calculate(num1, num2, operator);
                textField.setText(String.valueOf(result));
            } else {
                num1 = Double.parseDouble(textField.getText());
                operator = command.charAt(0);
                textField.setText("");
            }
        }
    }

    private double calculate(double num1, double num2, char operator) {
        switch (operator) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/': return num2 != 0 ? num1 / num2 : 0;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}