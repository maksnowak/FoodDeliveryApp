package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    private App parent;

    public MainMenuPanel(App parent) {
        this.parent = parent;

        JButton orderButton = new JButton("Zamów");
        JButton accountButton = new JButton("Konto");
        JButton restaurantsButton = new JButton("Restauracje");

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Panel z listą dostępnych restauracji");
            }
        });

        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Panel informacji o koncie");
            }
        });

        restaurantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Panel z listą zarządzanych restauracji (dla pracowników)");
            }
        });

        setLayout(new GridLayout(4, 1));
        add(new JLabel("Food!! (main menu)", SwingConstants.CENTER));
        add(orderButton);
        add(accountButton);
        add(restaurantsButton);
    }
}
