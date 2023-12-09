package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public CardLayout cardLayout;

    public App() {
        setTitle("PAP2023Z - Z09");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        WelcomePanel welcomePanel = new WelcomePanel(this);
        LoginPanel loginPanel = new LoginPanel(this);
        RegisterPanel registerPanel = new RegisterPanel(this);
        MainMenuPanel mainMenuPanel = new MainMenuPanel(this);

        add(welcomePanel, "Welcome");
        add(loginPanel, "Login");
        add(registerPanel, "Register");
        add(mainMenuPanel, "MainMenu");

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
