package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountInfoPanel extends FoodPanel {
    public AccountInfoPanel(Callback callback) {
        setLayout(new BorderLayout());

        JTextArea accountInfoTextArea = new JTextArea();
        accountInfoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(accountInfoTextArea);
        add(scrollPane, BorderLayout.CENTER);

        FoodButton editButton = new FoodButton("Edytuj dane");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "EditAccount");
            }
        });
        FoodButton deleteButton = new FoodButton("Usuń konto");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć konto?", "Potwierdzenie usunięcia konta", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    callback.onDeleteAccount();
                }
            }
        });

        FoodButton logoutButton = new FoodButton("Wyloguj");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz się wylogować?", "Potwierdzenie wylogowania", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    callback.onAccountLogout();
                }
            }
        });

        FoodButton backButton = new FoodButton("Powrót");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
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
