package pl.foodapp.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WelcomePanel extends JPanel {
    private JLabel clockLabel;
    public WelcomePanel(App parent) {
        JLabel welcomeLabel = new JLabel("FoodDeliveryApp", SwingConstants.CENTER);
        JButton loginButton = new JButton("Login");
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

        setLayout(new GridLayout(5, 1));
        add(welcomeLabel);
        add(loginButton);
        add(registerButton);
        add(new JLabel("", SwingConstants.CENTER));

        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        add(clockLabel, BorderLayout.NORTH, 4);
        timer.start();
    }
    private void updateClock() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(format.format(new Date()));
    }
}
