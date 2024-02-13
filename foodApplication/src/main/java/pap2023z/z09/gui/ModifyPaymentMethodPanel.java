package pap2023z.z09.gui;

import pap2023z.z09.accounts.EmailAlreadyExistsException;
import pap2023z.z09.database.AccountsEntity;
import pap2023z.z09.database.PaymentMethodsEntity;
import pap2023z.z09.paymentMethods.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class ModifyPaymentMethodPanel extends JPanel {
    JTextField numberField = new JTextField(15);
    JTextField monthField = new JTextField(2);
    JTextField yearField = new JTextField(2);
    JTextField CVVField = new JTextField(15);
    int paymentMethodId;

    public ModifyPaymentMethodPanel(Callback callback) {

        JButton modifyButton = new JButton("Zmodyfikuj");
        JButton returnButton = new JButton("Powrót");

        modifyButton.addActionListener(new ActionListener() {
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
                PaymentMethodsDTO methodDTO = new PaymentMethodsDTO(paymentMethodId, number, date, CVV, account.getAccountId());
                PaymentMethodsDAO methodDAO = new PaymentMethodsDAO();
                CreditCardValidationService CCVS = new CreditCardValidationService();
                VerifyIfCustomerAlreadyAddedCardService VIAACS = new VerifyIfCustomerAlreadyAddedCardService(methodDAO);
                ModifyCreditCardService MCCS = new ModifyCreditCardService(methodDAO, CCVS, VIAACS);
                try {
                    MCCS.modifyCreditCard(methodDTO);
                } catch (IllegalArgumentException | ExpiredCardException | CardAlreadyAddedException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
                JOptionPane.showMessageDialog(null, "Zmodyfikowano metodę płatności");







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
        add(new JLabel("Numer karty: "));
        add(numberField);
        add(new JLabel("Miesiąc: "));
        add(monthField);
        add(new JLabel("Rok: "));
        add(yearField);
        add(new JLabel("CVV: "));
        add(CVVField);
        add(returnButton);
        add(modifyButton);
    }

    public void enter(int id) {
        paymentMethodId = id;
        PaymentMethodsDAO methodDAO = new PaymentMethodsDAO();
        PaymentMethodsEntity paymentMethod = methodDAO.getMethodIdById(id);
        numberField.setText(paymentMethod.getCardNumber());
        monthField.setText(paymentMethod.getExpiryDate().toString().substring(5, 7));
        yearField.setText(paymentMethod.getExpiryDate().toString().substring(2, 4));
        CVVField.setText(paymentMethod.getCvv());
    }
}
