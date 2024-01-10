package pap2023z.z09.gui;

import pap2023z.z09.accounts.AccountsDAO;
import pap2023z.z09.accounts.LoginService;
import pap2023z.z09.baskets.BasketsDAO;
import pap2023z.z09.database.BasketsEntity;
import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.database.PaymentMethodsEntity;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;
import pap2023z.z09.dishes.orderedDishes.AddOrderedDishes;
import pap2023z.z09.paymentMethods.PaymentMethodsDAO;
import pap2023z.z09.orders.OrderHandler;
import pap2023z.z09.orders.OrdersDAO;
import pap2023z.z09.orders.OrdersDTO;
import pap2023z.z09.discounts.DiscountsDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class PaymentPanel extends JPanel {
    OrdersDAO ordersDAO = new OrdersDAO();
    BasketsDAO basketsDAO = new BasketsDAO();
    DishesDAO dishesDAO = new DishesDAO();
    OrderedDishesDAO orderedDishesDAO = new OrderedDishesDAO();
    OrderHandler orderHandler = new OrderHandler(ordersDAO);
    java.util.List<PaymentMethodsEntity> paymentMethods;
    int accountId;
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
                PaymentMethodsEntity paymentMethod;
                int comboBoxSelection = paymentMethodComboBox.getSelectedIndex();
                if (comboBoxSelection == -1) {
                    errorLabel.setText("Wybierz metodę płatności");
                    return;
                } else {
                    paymentMethod = paymentMethods.get(paymentMethodComboBox.getSelectedIndex());
                }
                String street = streetField.getText();
                if (street.isEmpty()) {
                    errorLabel.setText("Podaj ulicę");
                    return;
                }
                int streetNumber;
                try {
                    streetNumber = Integer.parseInt(streetNumberField.getText());
                } catch (NumberFormatException ex) {
                    errorLabel.setText("Podaj numer domu");
                    return;
                }
                if (streetNumber < 1) {
                    errorLabel.setText("Podaj prawidłowy numer domu");
                    return;
                }
                int apartment;
                if (apartmentField.getText().isEmpty()) {
                    apartment = 0;
                } else {
                    try {
                        apartment = Integer.parseInt(apartmentField.getText());
                    } catch (NumberFormatException ex) {
                        errorLabel.setText("Podaj prawidłowy numer mieszkania");
                        return;
                    }
                }
                String city = cityField.getText();
                if (city.equals("")) {
                    errorLabel.setText("Podaj miasto");
                    return;
                }
                Integer discountCode;
                if (discountCodeField.getText().isEmpty()) {
                    discountCode = null;
                } else {
                    try {
                        DiscountsDAO discountsDAO = new DiscountsDAO();
                        discountCode = discountsDAO.getDiscountByCode(discountCodeField.getText()).getDiscountId();
                    } catch (NullPointerException ex) {
                        errorLabel.setText("Podaj prawidłowy kod rabatowy");
                        return;
                    }
                }
                BigDecimal tip;
                if (tipField.getText().isEmpty()) {
                    tip = new BigDecimal(0);
                } else {
                    try {
                        tip = new BigDecimal(tipField.getText());
                    } catch (NumberFormatException ex) {
                        errorLabel.setText("Podaj prawidłowy napiwek");
                        return;
                    }
                }

                BigDecimal total = new BigDecimal(0);
                java.util.List<BasketsEntity> baskets = basketsDAO.getAllDishesOfClientId(accountId);
                for (BasketsEntity basket : baskets) {
                    DishesEntity dish = dishesDAO.getDishById(basket.getDishId());
                    total = total.add(dish.getPrice());
                }
                total = total.add(tip);

                AddOrderedDishes addOrderedDishes = new AddOrderedDishes(orderedDishesDAO, basketsDAO, accountId);

                OrdersDTO order = new OrdersDTO(0, 1, accountId, total, paymentMethod.getMethodId(), street, streetNumber, apartment, city, discountCode, tip);
                int newOrderId = orderHandler.addOrder(order);

                addOrderedDishes.addOrderedDishes(newOrderId);

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
        this.accountId = accountId;
        paymentMethodComboBox.removeAllItems();
        PaymentMethodsDAO paymentMethodsDAO = new PaymentMethodsDAO();
        paymentMethods = paymentMethodsDAO.getMethodsByCustomerId(accountId);
        for (PaymentMethodsEntity paymentMethod : paymentMethods) {
            paymentMethodComboBox.addItem(paymentMethod.getCardNumber());
        }
    }
}
