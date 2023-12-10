package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.restaurants.RestaurantsDAO;
import pap2023z.z09.restaurants.RestaurantsDTO;

public class RestaurantChoicePanel extends FoodPanel {
    private Callback callback;
    RestaurantsDAO RD = new RestaurantsDAO();
    List<RestaurantsEntity> restaurants = RD.getAllRestaurants();
    private JList<String> restaurantList;

    public RestaurantChoicePanel(Callback callback) {
        this.callback = callback;

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Wybierz restaurację:");
        add(titleLabel, BorderLayout.NORTH);

        restaurantList = new JList<>(restaurants.stream().map(RestaurantsEntity::getName).toArray(String[]::new));
        restaurantList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        restaurantList.addListSelectionListener(e -> {
            RestaurantsDAO RD = new RestaurantsDAO();
            callback.onRestaurantSelected(RD.getRestaurantByName(restaurantList.getSelectedValue()));
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
