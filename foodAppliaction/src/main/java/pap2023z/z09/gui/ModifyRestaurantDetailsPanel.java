package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.restaurants.RestaurantsDAO;
import pap2023z.z09.dishes.*;

public class ModifyRestaurantDetailsPanel extends JPanel {
    private JTextField nameField;
    private JTextField openingWeekdaysField;
    private JTextField openingWeekendsField;
    private JButton addDishButton;
    private JButton removeDishButton;
    private JButton changeOpensWeekdaysButton;
    private JButton changeClosesWeekdaysButton;
    private JButton changeOpensWeekendsButton;
    private JButton changeClosesWeekendsButton;
    private RestaurantsEntity restaurant;
    private DishesDAO dishesDAO;
    private JLabel titleLabel;

    public ModifyRestaurantDetailsPanel(Callback callback) {
        setLayout(new BorderLayout());

        titleLabel = new JLabel(); // Initialize titleLabel
        add(titleLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));

        addDishButton = new JButton("Add Dish");
        addDishButton.addActionListener(e -> {
            // Add code here to add a dish
            // You might want to open a new panel to get the dish details
        });
        buttonPanel.add(addDishButton);

        removeDishButton = new JButton("Remove Dish");
        removeDishButton.addActionListener(e -> {
            // Add code here to remove a dish
            // You might want to open a new panel to get the dish details
        });
        buttonPanel.add(removeDishButton);

        changeOpensWeekdaysButton = new JButton("Change Opens Weekdays");
        changeOpensWeekdaysButton.addActionListener(e -> {
            // Add code here to change opens weekdays
        });
        buttonPanel.add(changeOpensWeekdaysButton);

        changeClosesWeekdaysButton = new JButton("Change Closes Weekdays");
        changeClosesWeekdaysButton.addActionListener(e -> {
            // Add code here to change closes weekdays
        });
        buttonPanel.add(changeClosesWeekdaysButton);

        changeOpensWeekendsButton = new JButton("Change Opens Weekends");
        changeOpensWeekendsButton.addActionListener(e -> {
            // Add code here to change opens weekends
        });
        buttonPanel.add(changeOpensWeekendsButton);

        changeClosesWeekendsButton = new JButton("Change Closes Weekends");
        changeClosesWeekendsButton.addActionListener(e -> {
            // Add code here to change closes weekends
        });
        buttonPanel.add(changeClosesWeekendsButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setRestaurant(RestaurantsEntity restaurant) {
        this.restaurant = restaurant;
        titleLabel.setText("Modify " + restaurant.getName() + " Details:"); // Update titleLabel text
    }
}