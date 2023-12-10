package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;

import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.dishes.DishesDAO;

public class DishSelectionPanel extends JPanel {
    private Callback callback;
    DishesDAO DD = new DishesDAO();
    String[] dishes = DD.getAllDishes().stream().map(DishesEntity::getName).toArray(String[]::new);

    public DishSelectionPanel(Callback callback) {
        this.callback = callback;

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Wybierz danie w restauracji: ");
        add(titleLabel, BorderLayout.NORTH);

        JList<String> dishList = new JList<>(dishes);
        dishList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dishList.addListSelectionListener(e -> {
            String selectedDish = dishList.getSelectedValue();
            JOptionPane.showMessageDialog(null, "Wybrano danie: " + selectedDish);
        });
        JScrollPane scrollPane = new JScrollPane(dishList);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            callback.onRestaurantSelected(null);
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "RestaurantChoice");
        });
        add(backButton, BorderLayout.SOUTH);
    }

    public void updateRestaurantLabel(String restaurantName) {
        ((JLabel) getComponent(0)).setText("Wybierz danie w restauracji: " + restaurantName);
    }
}
