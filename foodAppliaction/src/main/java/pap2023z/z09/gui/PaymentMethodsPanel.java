package pap2023z.z09.gui;

import pap2023z.z09.database.PaymentMethodsEntity;
import pap2023z.z09.paymentMethods.PaymentMethodsDAO;

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
    List<PaymentMethodsEntity> paymentMethods;
    JList<String> paymentMethodsList;
    DefaultListModel<String> model = new DefaultListModel<>();
    JTextField searchField = new JTextField();
    private JLabel clockLabel;

    public PaymentMethodsPanel(Callback callback) {

        setLayout(new BorderLayout());
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchList();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                searchList();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                searchList();
            }
        });

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(3, 1));
        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        upperPanel.add(clockLabel);
        timer.start();
        JLabel titleLabel = new JLabel("Wybierz metodę płatności:");
        upperPanel.add(titleLabel);
        upperPanel.add(searchField);
        add(upperPanel, BorderLayout.NORTH);
        
        paymentMethodsList = new JList<>(model);

        paymentMethodsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        paymentMethodsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && paymentMethodsList.getSelectedValue() != null) {
                    String selected = paymentMethodsList.getSelectedValue();
                    paymentMethodsList.clearSelection();
                    searchField.setText("");
                    ((App) callback).cardLayout.show(((App) callback).getContentPane(), "PaymentMethodDetails");
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(paymentMethodsList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            paymentMethodsList.clearSelection();
            searchField.setText("");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
        });
        bottomPanel.add(backButton);

        JButton basketButton = new JButton("Dodaj metodę płatności");
        basketButton.addActionListener(e -> {
            searchField.setText("");
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
        paymentMethods = DAO.getMethodsByCustomerId(accountId);
        model.clear();
        for (PaymentMethodsEntity paymentMethod : paymentMethods) {
            model.addElement(paymentMethod.getCardNumber());
        }
    }

    public void searchList() {
        model.clear();
        String search = searchField.getText();
        for (PaymentMethodsEntity paymentMethod : paymentMethods) {
            if (paymentMethod.getCardNumber().contains(search.toLowerCase())) {
                model.addElement(paymentMethod.getCardNumber());
            }
        }
    }
}
