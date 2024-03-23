package pl.foodapp.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.foodapp.accounts.EmailAlreadyExistsException;

public class EditAccountPanel extends JPanel {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public EditAccountPanel(Callback callback) {
        setLayout(new BorderLayout());

        String currentFirstName = "name";
        String currentLastName = "surname";
        String currentEmail = "mail@example.com";
        String currentPassword = "password";

        firstNameField = new JTextField(currentFirstName);
        lastNameField = new JTextField(currentLastName);
        emailField = new JTextField(currentEmail);
        passwordField = new JPasswordField(currentPassword);

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Surname:"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        add(formPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save changes");
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
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "AccountInfo");
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setFields(String firstName, String lastName, String email, String password) {
        firstNameField.setText(firstName);
        lastNameField.setText(lastName);
        emailField.setText(email);
        passwordField.setText(password);
    }
}
