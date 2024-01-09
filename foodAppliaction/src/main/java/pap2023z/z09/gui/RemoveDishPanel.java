package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.dishes.*;

public class RemoveDishPanel extends JPanel {
    private JList<String> dishList;
    private JButton removeButton;
    private DishesDAO dishesDAO;

    public RemoveDishPanel(Callback callback) {
        dishesDAO = new DishesDAO();
        RestaurantsEntity selectedRestaurant = ((App) callback).selectedRestaurant;
        List<DishesEntity> dishes = dishesDAO.getDishesByRestaurant(selectedRestaurant.getRestaurantId());

        setLayout(new BorderLayout());

        dishList = new JList<>(dishes.stream().map(DishesEntity::getName).toArray(String[]::new));
        add(new JScrollPane(dishList), BorderLayout.CENTER);

        removeButton = new JButton("Usuń danie");
        removeButton.addActionListener(e -> {
            String selectedDish = dishList.getSelectedValue();
            if (selectedDish != null) {
                int response = JOptionPane.showConfirmDialog(this, "Na pewno chesz usunąć " + selectedDish + "?", "Potwierdź", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    DishesEntity dish = dishesDAO.getDishByName(selectedDish);
                    dishesDAO.removeDish(dish.getDishId());
                    JOptionPane.showMessageDialog(this, "Usunięto danie.");
                }
            }
        });
        add(removeButton, BorderLayout.SOUTH);
    }
}