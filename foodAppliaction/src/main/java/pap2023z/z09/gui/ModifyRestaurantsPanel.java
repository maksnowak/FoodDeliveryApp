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
    JList<String> restaurantList;

    JButton removeRestaurantButton = new JButton("Usuń restaurację");
    JButton modifyRestaurantButton = new JButton("Modyfikuj restaurację");
    JButton backButton = new JButton("Powrót");
    JButton addNewRestaurantButton = new JButton("Dodaj restaurację");
    DefaultListModel<String> restaurantListModel;

    public ModifyRestaurantsPanel(Callback callback) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Dodanie tytułu
        JLabel titleLabel = new JLabel("Wybierz restaurację do modyfikacji:");
        add(titleLabel);

        restaurantListModel = new DefaultListModel<>();
        restaurantList = new JList<>(restaurantListModel);
        refreshRestaurantList();
        add(new JScrollPane(restaurantList));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));

        // Dodanie przycisku do usuwania restauracji
        removeRestaurantButton.addActionListener(e -> {
            String selectedRestaurantName = restaurantList.getSelectedValue();
            if (selectedRestaurantName != null) {
                RestaurantsEntity restaurant = RD.getRestaurantByName(selectedRestaurantName);
                RD.removeRestaurant(restaurant.getRestaurantId());
                refreshRestaurantList();
                JOptionPane.showMessageDialog(this, "Usunięto restaurację.");
                restaurantList.clearSelection();
            }
        });
        buttonPanel.add(removeRestaurantButton);

        // Dodanie przycisku do modyfikacji restauracji
        modifyRestaurantButton.addActionListener(e -> {
            String selectedRestaurantName = restaurantList.getSelectedValue();
            if (selectedRestaurantName != null) {
                RestaurantsEntity restaurant = RD.getRestaurantByName(selectedRestaurantName);
                ((App) callback).selectedRestaurant = restaurant;
                restaurantList.clearSelection();
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
            }
        });
        buttonPanel.add(modifyRestaurantButton);

        // Przycisk powrotu
        backButton.addActionListener(e -> {
            restaurantList.clearSelection();
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
        });
        buttonPanel.add(backButton);

        // Dodanie przycisku do dodawania restauracji
        addNewRestaurantButton.addActionListener(e -> {
            restaurantList.clearSelection();
            AddNewRestaurantPanel addNewRestaurantPanel = new AddNewRestaurantPanel(new AddRestaurant(new RestaurantsDAO()), callback);
            ((App) callback).add(addNewRestaurantPanel, "AddNewRestaurant");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "AddNewRestaurant");
            refreshRestaurantList();
        });
        buttonPanel.add(addNewRestaurantButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    void refreshRestaurantList() {
        restaurants = RD.getAllRestaurants();
        restaurantListModel.clear();
        for (RestaurantsEntity restaurant : restaurants) {
            restaurantListModel.addElement(restaurant.getName());
        }
    }
}