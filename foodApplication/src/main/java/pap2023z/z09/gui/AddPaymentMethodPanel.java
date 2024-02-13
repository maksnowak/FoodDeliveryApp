package pap2023z.z09.gui;

import pap2023z.z09.accounts.*;
import pap2023z.z09.database.AccountsEntity;
import pap2023z.z09.paymentMethods.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class AddPaymentMethodPanel extends JPanel {
        public AddPaymentMethodPanel(Callback callback) {
            JLabel numberLabel = new JLabel("Numer karty:");
            JLabel CVVLabel = new JLabel("CVV:");
            JTextField numberField = new JTextField(15);
            JTextField monthField = new JTextField(2);
            JTextField yearField = new JTextField(2);
            JTextField CVVField = new JTextField(15);
            JButton addButton = new JButton("Dodaj metodę płatności");
            JButton returnButton = new JButton("Powrót");

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String number = numberField.getText();
                    String date_str = monthField.getText() + "/" + yearField.getText();
                    String CVV = CVVField.getText();

                    Date date;

                    SimpleDateFormat formatter = new SimpleDateFormat("MM/yy");
                    java.util.Date temp_date;
                    try {
                        temp_date = formatter.parse(date_str);
                        date = new Date(temp_date.getTime());
                    } catch (java.text.ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Niepoprawny format daty");
                        return;
                    }

                    AccountsEntity account = ((App) callback).loggedAccount;
                    PaymentMethodsDTO methodDTO = new PaymentMethodsDTO(0, number, date, CVV, account.getAccountId());
                    PaymentMethodsDAO methodDAO = new PaymentMethodsDAO();
                    CreditCardValidationService CCVS = new CreditCardValidationService();
                    VerifyIfCustomerAlreadyAddedCardService VIAACS = new VerifyIfCustomerAlreadyAddedCardService(methodDAO);
                    AddCreditCardService ACCS = new AddCreditCardService(methodDAO, CCVS, VIAACS);
                    try {
                        ACCS.addCreditCard(methodDTO);
                    } catch (IllegalArgumentException | ExpiredCardException | CardAlreadyAddedException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "Dodano metodę płatności");

                    numberField.setText("");
                    monthField.setText("");
                    yearField.setText("");
                    CVVField.setText("");
                    callback.enterPaymentMethods();
                }
            });

            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    numberField.setText("");
                    monthField.setText("");
                    yearField.setText("");
                    CVVField.setText("");
                    callback.enterPaymentMethods();
                }
            });

            setLayout(new GridLayout(6, 2));
            add(new JLabel("Dodaj kartę"));
            add(new JLabel());
            add(numberLabel);
            add(numberField);
            add(new JLabel("Miesiąc"));
            add(monthField);
            add(new JLabel("Rok"));
            add(yearField);
            add(CVVLabel);
            add(CVVField);
            add(returnButton);
            add(addButton);
        }
    }
