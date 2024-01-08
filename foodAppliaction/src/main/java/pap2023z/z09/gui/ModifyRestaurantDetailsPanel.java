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
    private JButton addDishButton;
    private JButton removeDishButton;
    private JButton changeOpensButton;
    private RestaurantsEntity restaurant;
    private DishesDAO dishesDAO;
    private JLabel titleLabel;

    public ModifyRestaurantDetailsPanel(Callback callback) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleLabel = new JLabel();
        add(titleLabel);
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));

        addDishButton = new JButton("Add Dish");
        addDishButton.addActionListener(e -> {
            // Add code here to add a dish
        });
        buttonPanel.add(addDishButton);

        removeDishButton = new JButton("Remove Dish");
        removeDishButton.addActionListener(e -> {
            // Add code here to remove a dish
        });
        buttonPanel.add(removeDishButton);

        changeOpensButton = new JButton("Change Opening Hours");
        changeOpensButton.addActionListener(e -> {
            ChangeHoursPanel changeHoursPanel = new ChangeHoursPanel(callback);
            RestaurantsEntity restaurant = ((App) callback).selectedRestaurant;
            changeHoursPanel.setRestaurant(restaurant);

            ((App) callback).add(changeHoursPanel, "ChangeHours");

            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ChangeHours");
        });
        buttonPanel.add(changeOpensButton);

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurants");
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setRestaurant(RestaurantsEntity restaurant) {
        this.restaurant = restaurant;
        titleLabel.setText("Modify " + restaurant.getName() + " Details:");
    }
}