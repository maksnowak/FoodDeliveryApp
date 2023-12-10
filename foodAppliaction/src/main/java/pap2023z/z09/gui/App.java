package pap2023z.z09.gui;

import pap2023z.z09.database.RestaurantsEntity;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame implements Callback {
    public CardLayout cardLayout;

    private DishSelectionPanel dishSelectionPanel;

    private String selectedRestaurant;

    public App() {
        setTitle("PAP2023Z - Z09");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        WelcomePanel welcomePanel = new WelcomePanel(this);
        LoginPanel loginPanel = new LoginPanel(this);
        RegisterPanel registerPanel = new RegisterPanel(this);
        MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
        RestaurantChoicePanel restaurantChoicePanel = new RestaurantChoicePanel(this);
        dishSelectionPanel = new DishSelectionPanel(this);

        add(welcomePanel, "Welcome");
        add(loginPanel, "Login");
        add(registerPanel, "Register");
        add(mainMenuPanel, "MainMenu");
        add(restaurantChoicePanel, "RestaurantChoice");
        add(dishSelectionPanel, "DishSelection");

        cardLayout.show(this.getContentPane(), "Welcome");
    }

    public String getSelectedRestaurant() {
        return selectedRestaurant;
    }

    public void setSelectedRestaurant(String selectedRestaurant) {
        this.selectedRestaurant = selectedRestaurant;
    }

    @Override
    public void onRestaurantSelected(String restaurantName) {
        setSelectedRestaurant(restaurantName);
        cardLayout.show(getContentPane(), "DishSelection");
        dishSelectionPanel.updateRestaurantLabel(getSelectedRestaurant());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App().setVisible(true);
            }
        });
    }
}
