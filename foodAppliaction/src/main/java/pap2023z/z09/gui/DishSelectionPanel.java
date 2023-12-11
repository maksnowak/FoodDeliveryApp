package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.dishes.DishesDAO;

public class DishSelectionPanel extends JPanel {
    DishesDAO DD = new DishesDAO();
    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> dishList = new JList<>(model);
    boolean isListenerActive = false;


    public DishSelectionPanel(Callback callback) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Wybierz restaurację:");
        add(titleLabel, BorderLayout.NORTH);

        dishList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dishList.addListSelectionListener(e -> {
            if (isListenerActive) {
                System.out.println("Wybrano danie: " + dishList.getSelectedValue());
                String selectedDish = dishList.getSelectedValue();
                JOptionPane.showMessageDialog(null, "Wybrano danie: " + selectedDish);
            }
        });
        JScrollPane scrollPane = new JScrollPane(dishList);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            isListenerActive = false;
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "RestaurantChoice");
        });
        add(backButton, BorderLayout.SOUTH);
    }

    public void enter(RestaurantsEntity restaurant) {
        model.clear();
        isListenerActive = true;
        ((JLabel) getComponent(0)).setText("Wybierz danie w restauracji: " + restaurant.getName());
        List<DishesEntity> dishes = DD.getDishesByRestaurant(restaurant.getRestaurantId());
        for (DishesEntity dish : dishes) {
            model.addElement(dish.getName());
        }
    }
}
