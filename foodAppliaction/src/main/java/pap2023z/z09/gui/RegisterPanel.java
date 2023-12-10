package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pap2023z.z09.accounts.*;

public class RegisterPanel extends JPanel {
        public RegisterPanel(App parent) {
            JCheckBox employeeCheckbox = new JCheckBox("Employee");
            JLabel loginLabel = new JLabel("Login:");
            JLabel passwordLabel = new JLabel("Password:");
            JTextField loginField = new JTextField(15);
            JPasswordField passwordField = new JPasswordField(15);
            JButton registerButton = new JButton("Register");
            JButton returnButton = new JButton("Return");
            JLabel errorLabel = new JLabel();

            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = loginField.getText();
                    char[] password = passwordField.getPassword();
                    String enteredPassword = new String(password);

                    AccountsDAO accountsDAO = new AccountsDAO();
                    InputValidationService IVS = new InputValidationService();
                    SignUpService SS = new SignUpService(accountsDAO, IVS);
                    AccountsDTO account = new AccountsDTO(0, username, enteredPassword, employeeCheckbox.isSelected() ? 2 : 1, "a", "b");
                    try {
                        SS.signUp(account);
                    } catch (EmailAlreadyExistsException ex) {
                        errorLabel.setText("Email already exists");
                        return;
                    } catch (IllegalArgumentException ex) {
                        errorLabel.setText(ex.getMessage());
                        return;
                    }

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
            add(errorLabel);
            add(new JLabel());
            add(returnButton);
            add(registerButton);
        }
    }
