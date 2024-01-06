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

        restaurantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurants");
            }
        });

        setLayout(new GridLayout(6, 1));
        add(new JLabel("Food!! (main menu)", SwingConstants.CENTER));
        add(orderButton);
        add(accountButton);
        add(restaurantsButton);
        add(new JLabel("Zalogowano jako: ", SwingConstants.CENTER));
        restaurantsButton.setVisible(false);

        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();
        JPanel clockPanel = new JPanel(new BorderLayout());
        clockPanel.add(clockLabel, BorderLayout.NORTH);
        add(clockPanel);
    }

    private void updateClock() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(format.format(new Date()));
    }

    public void updateAccountLabel(String accountName) {
        ((JLabel) getComponent(4)).setText("Zalogowano jako: " + accountName);
    }

    public void showRestaurantsButton() {
//        setBackground(new Color(42, 0, 93));
        getComponent(3).setVisible(true);
    }

    public void hideRestaurantsButton() {
//        setBackground(new Color(68, 0, 93));
        getComponent(3).setVisible(false);
    }
}
