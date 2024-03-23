package pl.foodapp.gui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

import pl.foodapp.database.DiscountsEntity;
import pl.foodapp.discounts.DiscountsDAO;

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
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Discount:"));
        discountField = new JTextField();
        add(discountField);

        // Add back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e2 -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
        });
        add(backButton);

        // Add button to add discount
        addButton = new JButton("Add discount");
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            BigDecimal discount = new BigDecimal(discountField.getText());

            DiscountsEntity DiscountsEntity = new DiscountsEntity();
            DiscountsEntity.setCode(name);
            DiscountsEntity.setDiscount(discount);
            discountsDAO.addDiscount(DiscountsEntity);

            // Show message about added discount and go back to modify restaurant details
            JOptionPane.showMessageDialog(this, "Discount added");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
        });
        add(addButton);
    }
}