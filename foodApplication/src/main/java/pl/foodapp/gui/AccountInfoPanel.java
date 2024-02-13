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

        JButton paymentMethodsButton = new JButton("Metody płatności");
        paymentMethodsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callback.enterPaymentMethods();
            }
        });

        JButton editButton = new JButton("Edytuj dane");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "EditAccount");
            }
        });
        JButton deleteButton = new JButton("Usuń konto");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć konto?", "Potwierdzenie usunięcia konta", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    callback.onDeleteAccount();
                }
            }
        });

        JButton logoutButton = new JButton("Wyloguj");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz się wylogować?", "Potwierdzenie wylogowania", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    callback.onAccountLogout();
                }
            }
        });

        JButton backButton = new JButton("Powrót");
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
