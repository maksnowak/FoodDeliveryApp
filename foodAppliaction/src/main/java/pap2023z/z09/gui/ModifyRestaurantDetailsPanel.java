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

        titleLabel = new JLabel("Zmień szczegóły restauracji:");
        add(titleLabel);
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));

        dishesDAO = new DishesDAO();
        addDishButton = new JButton("Dodaj danie");
        addDishButton.addActionListener(e -> {
            AddDishPanel addDishPanel = new AddDishPanel(new AddDish(dishesDAO), callback);
            ((App) callback).add(addDishPanel, "AddDish");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "AddDish");
        });
        buttonPanel.add(addDishButton);

        removeDishButton = new JButton("Usuń danie");
        removeDishButton.addActionListener(e -> {
            // Add code here to remove a dish
        });
        buttonPanel.add(removeDishButton);

        changeOpensButton = new JButton("Zmień godziny otwarcia");
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