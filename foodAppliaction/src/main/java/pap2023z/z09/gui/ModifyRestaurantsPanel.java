package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.restaurants.RestaurantsDAO;
import pap2023z.z09.restaurants.RestaurantsDTO;

public class ModifyRestaurantsPanel extends FoodPanel {
    RestaurantsDAO RD = new RestaurantsDAO();
    List<RestaurantsEntity> restaurants = RD.getAllRestaurants();
    private JList<String> restaurantList;

    public ModifyRestaurantsPanel(Callback callback) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Wybierz restaurację do modyfikacji:");
        add(titleLabel, BorderLayout.NORTH);

        restaurantList = new JList<>(restaurants.stream().map(RestaurantsEntity::getName).toArray(String[]::new));
        restaurantList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        restaurantList.addListSelectionListener(e -> {
            JOptionPane.showMessageDialog(null, restaurantList.getSelectedValue() + " została wybrana do modyfikacji");
        });
        JScrollPane scrollPane = new JScrollPane(restaurantList);
        add(scrollPane, BorderLayout.CENTER);

        FoodButton backButton = new FoodButton("Powrót");
        backButton.addActionListener(e -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
        });
        add(backButton, BorderLayout.SOUTH);
    }
}
