package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pap2023z.z09.accounts.AccountsDAO;
import pap2023z.z09.accounts.LoginService;

public class LoginPanel extends JPanel {
    public LoginPanel(Callback callback) {
        JLabel loginLabel = new JLabel("Login:");
        JLabel passwordLabel = new JLabel("Hasło:");
        JTextField loginField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Zaloguj");
        JButton returnButton = new JButton("Powrót");
        JLabel errorLabel = new JLabel();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = loginField.getText();
                char[] password = passwordField.getPassword();
                String enteredPassword = new String(password);

                AccountsDAO accountsDAO = new AccountsDAO();
                LoginService LS = new LoginService(accountsDAO);

                if (LS.login(email, enteredPassword)) {
                    callback.onAccountLogged(accountsDAO.getAccountByEmail(email));
                    loginField.setText("");
                    passwordField.setText("");
                    errorLabel.setText("");
                }
                else {
                    errorLabel.setText("Nieprawidłowe dane logowania");
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginField.setText("");
                passwordField.setText("");
                errorLabel.setText("");
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "Welcome");
            }
        });

        setLayout(new GridLayout(5, 2));
        add(new JLabel("Zaloguj się:"));
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
