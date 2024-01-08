package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.restaurants.RestaurantsDAO;

public class ModifyRestaurantDetailsPanel extends JPanel {
    private JTextField nameField;
    private JButton saveButton;
    private RestaurantsEntity restaurant;

    public ModifyRestaurantDetailsPanel(Callback callback) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Modify Restaurant Details:");
        add(titleLabel, BorderLayout.NORTH);

        nameField = new JTextField();
        add(nameField, BorderLayout.CENTER);

        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            restaurant.setName(nameField.getText());
            RestaurantsDAO dao = new RestaurantsDAO();
            dao.updateRestaurant(restaurant);
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurants");
        });
        add(saveButton, BorderLayout.SOUTH);
    }

    public void setRestaurant(RestaurantsEntity restaurant) {
        this.restaurant = restaurant;
        nameField.setText(restaurant.getName());
    }
}