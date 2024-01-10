package pap2023z.z09.gui;

import pap2023z.z09.accounts.AccountsDAO;
import pap2023z.z09.accounts.LoginService;
import pap2023z.z09.database.PaymentMethodsEntity;
import pap2023z.z09.paymentMethods.PaymentMethodsDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentPanel extends JPanel {
    java.util.List<PaymentMethodsEntity> paymentMethods;
    JComboBox<String> paymentMethodComboBox = new JComboBox<>();
    JTextField streetField = new JTextField();
    JTextField streetNumberField = new JTextField();
    JTextField apartmentField = new JTextField();
    JTextField cityField = new JTextField();
    JTextField discountCodeField = new JTextField();
    JTextField tipField = new JTextField();

    JButton orderButton = new JButton("ZAMÓW");
    JButton returnButton = new JButton("Return");
    JLabel errorLabel = new JLabel();

    public PaymentPanel(Callback callback) {
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaymentMethodsEntity paymentMethod = paymentMethods.get(paymentMethodComboBox.getSelectedIndex());
                String street = streetField.getText();
                String streetNumber = streetNumberField.getText();
                String apartment = apartmentField.getText();
                String city = cityField.getText();
                String discountCode = discountCodeField.getText();
                String tip = tipField.getText();

                // TODO: ORDER

                if (paymentMethodComboBox.getItemCount() != 0) {
                    paymentMethodComboBox.setSelectedIndex(0);
                }
                streetField.setText("");
                streetNumberField.setText("");
                apartmentField.setText("");
                cityField.setText("");
                discountCodeField.setText("");
                tipField.setText("");
                errorLabel.setText("");
                JOptionPane.showMessageDialog(null, "Zamówienie zostało złożone.");
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paymentMethodComboBox.getItemCount() != 0) {
                    paymentMethodComboBox.setSelectedIndex(0);
                }
                streetField.setText("");
                streetNumberField.setText("");
                apartmentField.setText("");
                cityField.setText("");
                discountCodeField.setText("");
                tipField.setText("");
                errorLabel.setText("");
                callback.enterBasket();
            }
        });

        setLayout(new GridLayout(10, 2));
        add(new JLabel("Zapłać:"));
        add(new JLabel());
        add(new JLabel("Metoda płatności:"));
        add(paymentMethodComboBox);
        add(new JLabel("Ulica:"));
        add(streetField);
        add(new JLabel("Numer domu:"));
        add(streetNumberField);
        add(new JLabel("Numer mieszkania:"));
        add(apartmentField);
        add(new JLabel("Miasto:"));
        add(cityField);
        add(new JLabel("Kod rabatowy:"));
        add(discountCodeField);
        add(new JLabel("Napiwek:"));
        add(tipField);
        add(errorLabel);
        add(new JLabel());
        add(returnButton);
        add(orderButton);
    }

    public void enter(int accountId) {
        paymentMethodComboBox.removeAllItems();
        PaymentMethodsDAO paymentMethodsDAO = new PaymentMethodsDAO();
        paymentMethods = paymentMethodsDAO.getMethodsByCustomerId(accountId);
        for (PaymentMethodsEntity paymentMethod : paymentMethods) {
            paymentMethodComboBox.addItem(paymentMethod.getCardNumber());
        }
    }
}
