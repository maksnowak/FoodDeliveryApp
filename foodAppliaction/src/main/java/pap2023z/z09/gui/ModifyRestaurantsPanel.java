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
    private JButton removeRestaurantButton;
    private DefaultListModel<String> restaurantListModel;

    public ModifyRestaurantsPanel(Callback callback) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Dodanie tytułu
        JLabel titleLabel = new JLabel("Wybierz restaurację do modyfikacji:");
        add(titleLabel);

        restaurantListModel = new DefaultListModel<>();
        restaurantList = new JList<>(restaurantListModel);
        refreshRestaurantList();
        add(new JScrollPane(restaurantList));

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

        // Dodanie przycisku do dodawania restauracji
        addNewRestaurantButton = new JButton("Dodaj restaurację");
        addNewRestaurantButton.addActionListener(e -> {
            AddNewRestaurantPanel addNewRestaurantPanel = new AddNewRestaurantPanel(new AddRestaurant(new RestaurantsDAO()), callback);
            ((App) callback).add(addNewRestaurantPanel, "AddNewRestaurant");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "AddNewRestaurant");
            refreshRestaurantList();
        });
        buttonPanel.add(addNewRestaurantButton);

        // Dodanie przycisku do usuwania restauracji
        removeRestaurantButton = new JButton("Usuń restaurację");
        removeRestaurantButton.addActionListener(e -> {
            String selectedRestaurantName = restaurantList.getSelectedValue();
            if (selectedRestaurantName != null) {
                RestaurantsEntity restaurant = RD.getRestaurantByName(selectedRestaurantName);
                RD.removeRestaurant(restaurant.getRestaurantId());
                refreshRestaurantList();
                JOptionPane.showMessageDialog(this, "Usunięto restaurację.");
            }
        });
        buttonPanel.add(removeRestaurantButton);

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void refreshRestaurantList() {
        restaurants = RD.getAllRestaurants();
        restaurantListModel.clear();
        for (RestaurantsEntity restaurant : restaurants) {
            restaurantListModel.addElement(restaurant.getName());
        }
    }
}