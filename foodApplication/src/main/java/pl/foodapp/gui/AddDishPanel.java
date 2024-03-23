package pl.foodapp.gui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.dishes.AddDish;
import pl.foodapp.dishes.DishesDTO;

public class AddDishPanel extends JPanel {
    private JTextField nameField;
    private JComboBox<String> typeComboBox;
    private JCheckBox vegetarianCheckBox;
    private JTextField priceField;
    private JTextField kcalField;
    private JButton addButton;
    private AddDish addDish;

    public AddDishPanel(AddDish addDish, Callback callback) {
        this.addDish = addDish;

        // create a 6x2 grid
        setLayout(new GridLayout(6, 2));

        // add fields and labels
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Dish type:"));
        typeComboBox = new JComboBox<>();
        typeComboBox.addItem("Appetizer");
        typeComboBox.addItem("Soup");
        typeComboBox.addItem("Main course");
        typeComboBox.addItem("Dessert");
        typeComboBox.addItem("Side dish");
        typeComboBox.addItem("Salad");
        typeComboBox.addItem("Beverage");
        add(typeComboBox);

        add(new JLabel("Vegetarian:"));
        vegetarianCheckBox = new JCheckBox();
        add(vegetarianCheckBox);

        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Kcal:"));
        kcalField = new JTextField();
        add(kcalField);

        // Add back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e2 -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
        });
        add(backButton);

        // Add dish button
        addButton = new JButton("Add dish");
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            int typeId = typeComboBox.getSelectedIndex() + 1;
            boolean vegetarian = vegetarianCheckBox.isSelected();
            BigDecimal price = new BigDecimal(priceField.getText());
            BigDecimal kcal = new BigDecimal(kcalField.getText());

            DishesDTO dish = new DishesDTO();
            dish.setName(name);
            dish.setTypeId(typeId);
            dish.setVegetarian(vegetarian);
            dish.setPrice(price);
            dish.setKcal(kcal);

            RestaurantsEntity restaurant = ((App) callback).selectedRestaurant;
            dish.setRestaurantId(restaurant.getRestaurantId());
            int dishId = addDish.addDish(dish);
            dish.setDishId(dishId);

            // Show message about added dish and go back to modify restaurant details
            JOptionPane.showMessageDialog(this, "Dish added.");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");


        });
        add(addButton);
    }
}