package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    public LoginPanel(App parent) {
        JLabel loginLabel = new JLabel("Login:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField loginField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Log in");
        JButton returnButton = new JButton("Return");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Login Successful!");
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
        add(new JLabel("Log into account"));
        add(new JLabel());
        add(loginLabel);
        add(loginField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel());
        add(new JLabel());
        add(returnButton);
        add(loginButton);

    }
}
