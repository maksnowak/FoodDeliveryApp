package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainMenuPanel extends JPanel {
    private JLabel clockLabel;
    public MainMenuPanel(Callback callback) {
        JButton orderButton = new JButton("Zamów");
        JButton accountButton = new JButton("Konto");
        JButton opinionButton = new JButton("Opinie");
        JButton complaintsButton = new JButton("Reklamacje");
        JButton orderStatusButton = new JButton("Status zamówienia");
        JButton restaurantsButton = new JButton("Restauracje");
        JButton statisticsButton = new JButton("Statystyki");


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

        opinionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "OpinionRestaurantChoice");
            }
        });

        restaurantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurants");
            }
        });

        setLayout(new GridLayout(10, 1));
        add(new JLabel("Food!! (main menu)", SwingConstants.CENTER));
        add(orderButton);
        add(accountButton);
        add(opinionButton);
        add(complaintsButton);
        add(orderStatusButton);
        add(restaurantsButton);
        add(statisticsButton);
        add(new JLabel("Zalogowano jako: ", SwingConstants.CENTER));
        restaurantsButton.setVisible(false);
        statisticsButton.setVisible(false);

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
        ((JLabel) getComponent(8)).setText("Zalogowano jako: " + accountName);
    }

    public void showRestaurantsButton() {
        getComponent(6).setVisible(true);
        getComponent(7).setVisible(true);
    }

    public void hideRestaurantsButton() {
        getComponent(6).setVisible(false);
        getComponent(7).setVisible(false);
    }
}
