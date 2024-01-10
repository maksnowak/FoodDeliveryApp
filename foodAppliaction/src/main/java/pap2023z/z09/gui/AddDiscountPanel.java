package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

import pap2023z.z09.database.DiscountsEntity;
import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.discounts.DiscountsDAO;

public class AddDiscountPanel extends JPanel {
    private JTextField nameField;
    private JTextField discountField;
    private JButton addButton;
    private DiscountsDAO discountsDAO;

    public AddDiscountPanel(Callback callback) {
        this.discountsDAO = new DiscountsDAO();

        // Set layout
        setLayout(new GridLayout(3, 2));

        // Add fields and labels
        add(new JLabel("Nazwa:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Rabat:"));
        discountField = new JTextField();
        add(discountField);

        // Add back button
        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e2 -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
        });
        add(backButton);

        // Add button to add discount
        addButton = new JButton("Dodaj rabat");
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            BigDecimal discount = new BigDecimal(discountField.getText());

            DiscountsEntity DiscountsEntity = new DiscountsEntity();
            DiscountsEntity.setCode(name);
            DiscountsEntity.setDiscount(discount);
            discountsDAO.addDiscount(DiscountsEntity);

            // Show message about added discount and go back to modify restaurant details
            JOptionPane.showMessageDialog(this, "Dodano rabat.");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
        });
        add(addButton);
    }
}