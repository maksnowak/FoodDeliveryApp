package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends FoodPanel {
    public MainMenuPanel(Callback callback) {
        FoodButton orderButton = new FoodButton("Zamów");
        FoodButton accountButton = new FoodButton("Konto");
        FoodButton restaurantsButton = new FoodButton("Restauracje");

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "RestaurantChoice");
            }
        });

        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "AccountInfo");
            }
        });

        restaurantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurants");
            }
        });

        setLayout(new GridLayout(5, 1));
        add(new JLabel("Food!! (main menu)", SwingConstants.CENTER));
        add(orderButton);
        add(accountButton);
        add(restaurantsButton);
        add(new JLabel("Zalogowano jako: ", SwingConstants.CENTER));
        restaurantsButton.setVisible(false);
    }

    public void updateAccountLabel(String accountName) {
        ((JLabel) getComponent(4)).setText("Zalogowano jako: " + accountName);
    }

    public void showRestaurantsButton() {
        setBackground(new Color(42, 0, 93));
        getComponent(3).setVisible(true);
    }

    public void hideRestaurantsButton() {
        setBackground(new Color(68, 0, 93));
        getComponent(3).setVisible(false);
    }
}
