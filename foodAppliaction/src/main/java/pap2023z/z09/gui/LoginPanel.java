package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pap2023z.z09.accounts.AccountsDAO;
import pap2023z.z09.accounts.LoginService;

public class LoginPanel extends JPanel {
    public LoginPanel(App parent) {
        JLabel loginLabel = new JLabel("Login:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField loginField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Log in");
        JButton returnButton = new JButton("Return");
        JLabel errorLabel = new JLabel();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginField.getText();
                char[] password = passwordField.getPassword();
                String enteredPassword = new String(password);

                AccountsDAO accountsDAO = new AccountsDAO();
                LoginService LS = new LoginService(accountsDAO);

                if (LS.login(username, enteredPassword)) {
                    parent.cardLayout.show(parent.getContentPane(), "MainMenu");
                }
                else {
                    errorLabel.setText("Incorrect username or password");
                }
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
        add(errorLabel);
        add(new JLabel());
        add(returnButton);
        add(loginButton);

    }
}
