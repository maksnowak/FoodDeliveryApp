package pap2023z.z09.gui;

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

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        JPanel editDeletePanel = new JPanel(new GridLayout(1, 2));
        editDeletePanel.add(editButton);
        editDeletePanel.add(deleteButton);

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
            }
        });
        buttonPanel.add(editDeletePanel);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateAccountInfo(String accountInfo) {
        ((JTextArea) ((JScrollPane) getComponent(0)).getViewport().getView()).setText(accountInfo);
    }
}
