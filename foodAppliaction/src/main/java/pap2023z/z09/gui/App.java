package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public CardLayout cardLayout;

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

        add(welcomePanel, "Welcome");
        add(loginPanel, "Login");
        add(registerPanel, "Register");
        add(mainMenuPanel, "MainMenu");
        add(restaurantChoicePanel, "RestaurantChoice");

        cardLayout.show(this.getContentPane(), "Welcome");
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
