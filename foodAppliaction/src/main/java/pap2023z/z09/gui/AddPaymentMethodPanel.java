package pap2023z.z09.gui;

import pap2023z.z09.accounts.*;
import pap2023z.z09.database.AccountsEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPaymentMethodPanel extends JPanel {
        public AddPaymentMethodPanel(Callback callback) {
            JLabel numberLabel = new JLabel("Numer karty:");
            JLabel dateLabel = new JLabel("Data ważności:");
            JLabel CVVLabel = new JLabel("CVV:");
            JTextField numberField = new JTextField(15);
            JTextField dateField = new JTextField(15);
            JTextField CVVField = new JTextField(15);
            JButton addButton = new JButton("Dodaj metodę płatności");
            JButton returnButton = new JButton("Powrót");

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String number = numberField.getText();
                    String date = dateField.getText();
                    String CVV = CVVField.getText();

                    AccountsDAO accountsDAO = new AccountsDAO();

                    // TODO: add payment method
                    JOptionPane.showMessageDialog(null, "Dodano metodę płatności");

                    numberField.setText("");
                    dateField.setText("");
                    CVVField.setText("");
                    callback.enterPaymentMethods();
                }
            });

            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    numberField.setText("");
                    dateField.setText("");
                    CVVField.setText("");
                    callback.enterPaymentMethods();
                }
            });

            setLayout(new GridLayout(5, 2));
            add(new JLabel("Dodaj kartę"));
            add(new JLabel());
            add(numberLabel);
            add(numberField);
            add(dateLabel);
            add(dateField);
            add(CVVLabel);
            add(CVVField);
            add(returnButton);
            add(addButton);
        }
    }
