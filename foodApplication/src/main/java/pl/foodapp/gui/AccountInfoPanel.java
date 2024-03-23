package pl.foodapp.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountInfoPanel extends JPanel {
    public AccountInfoPanel(Callback callback) {
        setLayout(new BorderLayout());

        JTextArea accountInfoTextArea = new JTextArea();
        accountInfoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(accountInfoTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton paymentMethodsButton = new JButton("Payment methods");
        paymentMethodsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callback.enterPaymentMethods();
            }
        });

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "EditAccount");
            }
        });
        JButton deleteButton = new JButton("Delete account");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete your account?", "Delete account confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    callback.onDeleteAccount();
                }
            }
        });

        JButton logoutButton = new JButton("Sign out");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to sign out?", "Sign out confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    callback.onAccountLogout();
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));
        buttonPanel.add(new JPanel());
        buttonPanel.add(paymentMethodsButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateAccountInfo(String accountInfo) {
        ((JTextArea) ((JScrollPane) getComponent(0)).getViewport().getView()).setText(accountInfo);
    }
}
