package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel {
    public WelcomePanel(App parent) {
        JLabel welcomeLabel = new JLabel("Food!!", SwingConstants.CENTER);
        JButton loginButton = new JButton("Log in");
        JButton registerButton = new JButton("Register");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.cardLayout.show(parent.getContentPane(), "Login");
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.cardLayout.show(parent.getContentPane(), "Register");
            }
        });

        setLayout(new GridLayout(4, 1));
        add(welcomeLabel);
        add(loginButton);
        add(registerButton);
        add(new JLabel("Copyright, yes yes", SwingConstants.CENTER));
    }
}
