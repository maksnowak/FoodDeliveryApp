package pl.foodapp.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainMenuPanel extends JPanel {
    private JLabel clockLabel;
    JButton orderButton = new JButton("Order");
    JButton accountButton = new JButton("Account");
    JButton historyButton = new JButton("History");
    JButton opinionButton = new JButton("Reviews");
    JButton favouritesButton = new JButton("Add to favourites");
    JButton restaurantsButton = new JButton("Restaurants");
    JLabel loggedAsLabel = new JLabel("Logged in as:");
    public MainMenuPanel(Callback callback) {
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callback.enterRestaurantChoicePanel();
            }
        });

        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "AccountInfo");
            }
        });

        opinionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "OpinionRestaurantChoice");
            }
        });

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callback.enterHistoryPanel();
            }
        });
        favouritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "favouritesRestaurantChoicePanel");
            }
        });
        restaurantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callback.enterModifyRestaurantPanel();
            }
        });



        setLayout(new GridLayout(9, 1));
        add(new JLabel("FoodDeliveryApp", SwingConstants.CENTER));
        add(orderButton);
        add(accountButton);
        add(historyButton);
        add(opinionButton);
        add(favouritesButton);
        add(restaurantsButton);

        add(loggedAsLabel);

        restaurantsButton.setVisible(false);

        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();
        add(clockLabel);
    }

    private void updateClock() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(format.format(new Date()));
    }

    public void updateAccountLabel(String accountName) {
        loggedAsLabel.setText("Logged in as: " + accountName);
        loggedAsLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    public void showRestaurantsButton() {
        restaurantsButton.setVisible(true);
    }

    public void hideRestaurantsButton() {
        restaurantsButton.setVisible(false);
    }
}
