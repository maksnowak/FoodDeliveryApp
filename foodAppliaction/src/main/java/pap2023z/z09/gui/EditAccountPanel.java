package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pap2023z.z09.accounts.EmailAlreadyExistsException;
import pap2023z.z09.database.AccountsEntity;

public class EditAccountPanel extends FoodPanel {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public EditAccountPanel(Callback callback) {
        setLayout(new BorderLayout());

        String currentFirstName = "imie";
        String currentLastName = "nazwisko";
        String currentEmail = "mail@example.com";
        String currentPassword = "haslo";

        firstNameField = new JTextField(currentFirstName);
        lastNameField = new JTextField(currentLastName);
        emailField = new JTextField(currentEmail);
        passwordField = new JPasswordField(currentPassword);

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Imię:"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Nazwisko:"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Hasło:"));
        formPanel.add(passwordField);

        add(formPanel, BorderLayout.CENTER);

        FoodButton saveButton = new FoodButton("Zapisz zmiany");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    firstNameField.getText();
                    System.out.println(firstNameField.getText() + " " + lastNameField.getText() + " " + emailField.getText());
                    callback.onEditAccount(firstNameField.getText(), lastNameField.getText(), emailField.getText(), String.copyValueOf(passwordField.getPassword()));
                } catch (EmailAlreadyExistsException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        FoodButton cancelButton = new FoodButton("Anuluj");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "AccountInfo");
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setFields(String firstName, String lastName, String email, String password) {
        firstNameField.setText(firstName);
        lastNameField.setText(lastName);
        emailField.setText(email);
        passwordField.setText(password);
    }
}
