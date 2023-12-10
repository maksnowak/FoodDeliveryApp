package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
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
                JOptionPane.showMessageDialog(null, "Panel z listą zarządzanych restauracji (dla pracowników)");
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
        getComponent(3).setVisible(true);
    }
}
