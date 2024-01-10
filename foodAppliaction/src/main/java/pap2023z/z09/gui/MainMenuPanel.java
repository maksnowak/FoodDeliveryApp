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
        JButton orderStatusButton = new JButton("Status zamówienia");
        JButton historyButton = new JButton("Historia i reklamacje");
        JButton opinionButton = new JButton("Opinie");
        JButton restaurantsButton = new JButton("Restauracje");

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

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callback.enterHistoryPanel();
            }
        });

        restaurantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callback.enterModifyRestaurantPanel();
            }
        });

        setLayout(new GridLayout(9, 1));
        add(new JLabel("Food!! (main menu)", SwingConstants.CENTER));
        add(orderButton);
        add(accountButton);
        add(orderStatusButton);
        add(historyButton);
        add(opinionButton);
        add(restaurantsButton);
        add(new JLabel("Zalogowano jako: ", SwingConstants.CENTER));
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
        ((JLabel) getComponent(8)).setText("Zalogowano jako: " + accountName);
    }

    public void showRestaurantsButton() {
        getComponent(6).setVisible(true);
    }

    public void hideRestaurantsButton() {
        getComponent(6).setVisible(false);
    }
}
