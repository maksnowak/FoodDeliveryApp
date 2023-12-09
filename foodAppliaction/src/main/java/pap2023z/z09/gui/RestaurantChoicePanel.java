package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaurantChoicePanel extends JPanel {
    private App parent;
    private JList<String> restaurantList;

    public RestaurantChoicePanel(App parent) {
        this.parent = parent;

        String[] restaurantNames = {"Restauracja A", "Restauracja B", "Restauracja C"};

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Wybierz restaurację:");
        add(titleLabel, BorderLayout.NORTH);

        restaurantList = new JList<>(restaurantNames);
        restaurantList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        restaurantList.addListSelectionListener(e -> {
            String selectedRestaurant = restaurantList.getSelectedValue();
            JOptionPane.showMessageDialog(null, "Wybrano restaurację: " + selectedRestaurant);

            // parent.cardLayout.show(parent.getContentPane(), "DishSelection");
        });
        JScrollPane scrollPane = new JScrollPane(restaurantList);
        add(scrollPane, BorderLayout.CENTER);
    }
}
