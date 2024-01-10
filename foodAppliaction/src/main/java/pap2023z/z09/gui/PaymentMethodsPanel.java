package pap2023z.z09.gui;

import pap2023z.z09.database.PaymentMethodsEntity;
import pap2023z.z09.paymentMethods.PaymentMethodsDAO;
import pap2023z.z09.paymentMethods.DeleteCreditCardService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PaymentMethodsPanel extends JPanel {
    PaymentMethodsDAO DAO = new PaymentMethodsDAO();
    int accountId;
    List<PaymentMethodsEntity> paymentMethods;
    JList<String> paymentMethodsList;
    DefaultListModel<String> model = new DefaultListModel<>();
    private JLabel clockLabel;

    public PaymentMethodsPanel(Callback callback) {

        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(2, 1));
        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        upperPanel.add(clockLabel);
        timer.start();
        JLabel titleLabel = new JLabel("Wybierz metodę płatności:");
        upperPanel.add(titleLabel);
        add(upperPanel, BorderLayout.NORTH);
        
        paymentMethodsList = new JList<>(model);

        paymentMethodsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(paymentMethodsList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 2));

        JButton deleteButton = new JButton("Usuń metodę płatności");
        deleteButton.addActionListener(e -> {
            int selectedId = paymentMethodsList.getSelectedIndex();
            if (selectedId != -1) {
                int actualId = paymentMethods.get(selectedId).getMethodId();
                String number = paymentMethods.get(selectedId).getCardNumber();
                DeleteCreditCardService deleteCreditCardService = new DeleteCreditCardService(DAO);
                deleteCreditCardService.deleteCreditCard(actualId);
                refreshPaymentMethods();
                JOptionPane.showMessageDialog(this, "Usunięto metodę płatności " + number + ".");
            }
        });
        bottomPanel.add(deleteButton);

        JButton modifyButton = new JButton("Modyfikuj metodę płatności");
        modifyButton.addActionListener(e -> {
            int selectedId = paymentMethodsList.getSelectedIndex();
            if (selectedId != -1) {
                int actualId = paymentMethods.get(selectedId).getMethodId();
                callback.enterModifyPaymentMethod(actualId);
            }
        });
        bottomPanel.add(modifyButton);

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            paymentMethodsList.clearSelection();
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "AccountInfo");
        });
        bottomPanel.add(backButton);

        JButton basketButton = new JButton("Dodaj metodę płatności");
        basketButton.addActionListener(e -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "AddPaymentMethod");
        });
        bottomPanel.add(basketButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateClock() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(format.format(new Date()));
    }

    public void enter(int accountId) {
        this.accountId = accountId;
        refreshPaymentMethods();
    }

    void refreshPaymentMethods() {
        paymentMethods = DAO.getMethodsByCustomerId(accountId);
        model.clear();
        for (PaymentMethodsEntity paymentMethod : paymentMethods) {
            model.addElement(paymentMethod.getCardNumber());
        }
    }
}
