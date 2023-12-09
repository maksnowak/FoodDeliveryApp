package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class RegisterPanel extends JPanel {
        public RegisterPanel(App parent) {
            JCheckBox employeeCheckbox = new JCheckBox("Employee");
            JLabel loginLabel = new JLabel("Login:");
            JLabel passwordLabel = new JLabel("Password:");
            JTextField loginField = new JTextField(15);
            JPasswordField passwordField = new JPasswordField(15);
            JButton registerButton = new JButton("Register");
            JButton returnButton = new JButton("Return");

            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Registration Successful!");
                    parent.cardLayout.show(parent.getContentPane(), "MainMenu");
                }
            });

            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parent.cardLayout.show(parent.getContentPane(), "Welcome");
                }
            });

            setLayout(new GridLayout(5, 2));
            add(new JLabel("Register new account"));
            add(employeeCheckbox);
            add(loginLabel);
            add(loginField);
            add(passwordLabel);
            add(passwordField);
            add(new JLabel());
            add(new JLabel());
            add(returnButton);
            add(registerButton);
        }
    }
