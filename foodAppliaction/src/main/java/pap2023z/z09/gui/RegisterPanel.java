package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pap2023z.z09.accounts.*;

public class RegisterPanel extends JPanel {
        public RegisterPanel(Callback callback) {
            JCheckBox employeeCheckbox = new JCheckBox("Employee");
            JLabel loginLabel = new JLabel("Login:");
            JLabel passwordLabel = new JLabel("Password:");
            JLabel nameLabel = new JLabel("Name:");
            JLabel surnameLabel = new JLabel("Surname:");
            JTextField loginField = new JTextField(15);
            JPasswordField passwordField = new JPasswordField(15);
            JTextField nameField = new JTextField(15);
            JTextField surnameField = new JTextField(15);
            JButton registerButton = new JButton("Register");
            JButton returnButton = new JButton("Return");
            JLabel errorLabel = new JLabel();

            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = loginField.getText();
                    char[] password = passwordField.getPassword();
                    String name = nameField.getText();
                    String surname = surnameField.getText();
                    String enteredPassword = new String(password);

                    AccountsDAO accountsDAO = new AccountsDAO();
                    InputValidationService IVS = new InputValidationService();
                    VerifyIfEmailAlreadyExistsService VIAES = new VerifyIfEmailAlreadyExistsService(accountsDAO);
                    SignUpService SS = new SignUpService(accountsDAO, IVS, VIAES);
                    AccountsDTO account = new AccountsDTO(0, username, enteredPassword, employeeCheckbox.isSelected() ? 2 : 1, name, surname);
                    try {
                        SS.signUp(account);
                    } catch (EmailAlreadyExistsException ex) {
                        errorLabel.setText("Podany email jest zajęty");
                        return;
                    } catch (IllegalArgumentException ex) {
                        errorLabel.setText(ex.getMessage());
                        return;
                    }

                    JOptionPane.showMessageDialog(null, "Zarejestrowano!");
                    loginField.setText("");
                    passwordField.setText("");
                    nameField.setText("");
                    surnameField.setText("");
                    errorLabel.setText("");
                    ((App) callback).cardLayout.show(((App) callback).getContentPane(), "Login");

                }
            });

            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loginField.setText("");
                    passwordField.setText("");
                    nameField.setText("");
                    surnameField.setText("");
                    errorLabel.setText("");
                    ((App) callback).cardLayout.show(((App) callback).getContentPane(), "Welcome");
                }
            });

            setLayout(new GridLayout(7, 2));
            add(new JLabel("Zarejestruj nowe konto:"));
            add(employeeCheckbox);
            add(loginLabel);
            add(loginField);
            add(passwordLabel);
            add(passwordField);
            add(nameLabel);
            add(nameField);
            add(surnameLabel);
            add(surnameField);
            add(errorLabel);
            add(new JLabel());
            add(returnButton);
            add(registerButton);
        }
    }
