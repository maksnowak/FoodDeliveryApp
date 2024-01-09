package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.restaurants.AddRestaurant;
import pap2023z.z09.restaurants.RestaurantsDAO;
import pap2023z.z09.restaurants.RestaurantsDTO;

public class ModifyRestaurantsPanel extends JPanel {
    RestaurantsDAO RD = new RestaurantsDAO();
    List<RestaurantsEntity> restaurants = RD.getAllRestaurants();
    private JList<String> restaurantList;
    private JButton addNewRestaurantButton;

    public ModifyRestaurantsPanel(Callback callback) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Wybierz restaurację do modyfikacji:");
        add(titleLabel, BorderLayout.NORTH);

        restaurantList = new JList<>(restaurants.stream().map(RestaurantsEntity::getName).toArray(String[]::new));
        restaurantList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        restaurantList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && restaurantList.getSelectedValue() != null) {
                String selected = restaurantList.getSelectedValue();
                restaurantList.clearSelection();
                RestaurantsDAO DAO = new RestaurantsDAO();
                RestaurantsEntity restaurant = DAO.getRestaurantByName(selected);
                ((App) callback).selectedRestaurant = restaurant;
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
            }
        });
        JScrollPane scrollPane = new JScrollPane(restaurantList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));

        addNewRestaurantButton = new JButton("Add New Restaurant");
        addNewRestaurantButton.addActionListener(e -> {
            AddNewRestaurantPanel addNewRestaurantPanel = new AddNewRestaurantPanel(new AddRestaurant(new RestaurantsDAO()), callback);
            ((App) callback).add(addNewRestaurantPanel, "AddNewRestaurant");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "AddNewRestaurant");
        });
        buttonPanel.add(addNewRestaurantButton);

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}