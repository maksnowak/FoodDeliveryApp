package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainMenuPanel extends JPanel {
    private JLabel clockLabel;
    JButton orderButton = new JButton("Zamów");
    JButton accountButton = new JButton("Konto");
    JButton historyButton = new JButton("Historia zamówień");
    JButton opinionButton = new JButton("Opinie");
    JButton favouritesButton = new JButton("Dodaj do ulubionych");
    JButton restaurantsButton = new JButton("Restauracje");
    JLabel loggedAsLabel = new JLabel("Zalogowano jako: ");
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
        add(new JLabel("Food!! (main menu)", SwingConstants.CENTER));
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
        loggedAsLabel.setText("Zalogowano jako: " + accountName);
        loggedAsLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    public void showRestaurantsButton() {
        restaurantsButton.setVisible(true);
    }

    public void hideRestaurantsButton() {
        restaurantsButton.setVisible(false);
    }
}
