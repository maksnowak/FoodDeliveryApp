package pl.foodapp.gui;

import pl.foodapp.baskets.BasketsDAO;
import pl.foodapp.database.BasketsEntity;
import pl.foodapp.database.DishesEntity;
import pl.foodapp.database.PaymentMethodsEntity;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDAO;
import pl.foodapp.dishes.orderedDishes.AddOrderedDishes;
import pl.foodapp.paymentMethods.PaymentMethodsDAO;
import pl.foodapp.orders.OrderHandler;
import pl.foodapp.orders.OrdersDAO;
import pl.foodapp.orders.OrdersDTO;
import pl.foodapp.discounts.DiscountsDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;

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

    JButton orderButton = new JButton("Order");
    JButton returnButton = new JButton("Return");
    JLabel errorLabel = new JLabel();

    public PaymentPanel(Callback callback) {
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaymentMethodsEntity paymentMethod;
                int comboBoxSelection = paymentMethodComboBox.getSelectedIndex();
                if (comboBoxSelection == -1) {
                    errorLabel.setText("Select payment method");
                    return;
                } else {
                    paymentMethod = paymentMethods.get(paymentMethodComboBox.getSelectedIndex());
                }
                String street = streetField.getText();
                if (street.isEmpty()) {
                    errorLabel.setText("Enter street name");
                    return;
                }
                int streetNumber;
                try {
                    streetNumber = Integer.parseInt(streetNumberField.getText());
                } catch (NumberFormatException ex) {
                    errorLabel.setText("Enter street number");
                    return;
                }
                if (streetNumber < 1) {
                    errorLabel.setText("Enter valid street number");
                    return;
                }
                int apartment;
                if (apartmentField.getText().isEmpty()) {
                    apartment = 0;
                } else {
                    try {
                        apartment = Integer.parseInt(apartmentField.getText());
                    } catch (NumberFormatException ex) {
                        errorLabel.setText("Enter valid apartment number");
                        return;
                    }
                }
                String city = cityField.getText();
                if (city.equals("")) {
                    errorLabel.setText("Enter city name");
                    return;
                }
                Integer discountCode;
                BigDecimal discount = new BigDecimal(0);
                if (discountCodeField.getText().isEmpty()) {
                    discountCode = null;
                } else {
                    try {
                        DiscountsDAO discountsDAO = new DiscountsDAO();
                        discountCode = discountsDAO.getDiscountByCode(discountCodeField.getText()).getDiscountId();
                        discount = discountsDAO.getDiscountByCode(discountCodeField.getText()).getDiscount();
                    } catch (NullPointerException ex) {
                        errorLabel.setText("Enter correct discount code");
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
                        errorLabel.setText("Enter correct tip amount");
                        return;
                    }
                }

                BigDecimal total = new BigDecimal(0);
                java.util.List<BasketsEntity> baskets = basketsDAO.getAllDishesOfClientId(accountId);
                for (BasketsEntity basket : baskets) {
                    DishesEntity dish = dishesDAO.getDishById(basket.getDishId());
                    total = total.add(dish.getPrice());
                }
                total = total.subtract(total.multiply(discount).divide(new BigDecimal(100)));
                total = total.round(new MathContext(2));
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
                JOptionPane.showMessageDialog(null, "Order placed successfully");
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
        add(new JLabel("Pay:"));
        add(new JLabel());
        add(new JLabel("Payment method:"));
        add(paymentMethodComboBox);
        add(new JLabel("Street:"));
        add(streetField);
        add(new JLabel("Street number:"));
        add(streetNumberField);
        add(new JLabel("Apartment:"));
        add(apartmentField);
        add(new JLabel("City:"));
        add(cityField);
        add(new JLabel("Discount code:"));
        add(discountCodeField);
        add(new JLabel("Tip:"));
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
