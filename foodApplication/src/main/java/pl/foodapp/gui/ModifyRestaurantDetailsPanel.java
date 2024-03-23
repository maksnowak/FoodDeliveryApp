package pl.foodapp.gui;

import javax.swing.*;
import java.awt.*;

import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.dishes.AddDish;
import pl.foodapp.dishes.DishesDAO;

public class ModifyRestaurantDetailsPanel extends JPanel {

    private JButton addDishButton;
    private JButton removeDishButton;
    private JButton changeOpensButton;
    private RestaurantsEntity restaurant;
    private DishesDAO dishesDAO;
    private JLabel titleLabel;

    public ModifyRestaurantDetailsPanel(Callback callback) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add title
        titleLabel = new JLabel("Change restaurant details");
        add(titleLabel);
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));

        // Add dish button
        dishesDAO = new DishesDAO();
        addDishButton = new JButton("Add dish");
        addDishButton.addActionListener(e -> {
            AddDishPanel addDishPanel = new AddDishPanel(new AddDish(dishesDAO), callback);
            ((App) callback).add(addDishPanel, "AddDish");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "AddDish");
        });
        buttonPanel.add(addDishButton);

        // Remove dish button
        removeDishButton = new JButton("Remove dish");
        removeDishButton.addActionListener(e -> {
            RemoveDishPanel removeDishPanel = new RemoveDishPanel(callback);
            ((App) callback).add(removeDishPanel, "RemoveDish");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "RemoveDish");
        });
        buttonPanel.add(removeDishButton);

        // Add button to change opening hours
        changeOpensButton = new JButton("Change opening hours");
        changeOpensButton.addActionListener(e -> {
            ChangeHoursPanel changeHoursPanel = new ChangeHoursPanel(callback);
            RestaurantsEntity restaurant = ((App) callback).selectedRestaurant;
            changeHoursPanel.setRestaurant(restaurant);

            ((App) callback).add(changeHoursPanel, "ChangeHours");

            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ChangeHours");
        });
        buttonPanel.add(changeOpensButton);

        // Add button to add discount codes
        JButton addDiscountButton = new JButton("Add discount code");
        addDiscountButton.addActionListener(e -> {
            AddDiscountPanel addDiscountPanel = new AddDiscountPanel(callback);
            ((App) callback).add(addDiscountPanel, "AddDiscount");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "AddDiscount");
        });
        buttonPanel.add(addDiscountButton);

        // Add back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurants");
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}