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
    private JButton backButton;
    private DishesDAO dishesDAO;
    private DefaultListModel<String> dishListModel;

    public RemoveDishPanel(Callback callback) {
        dishesDAO = new DishesDAO();
        RestaurantsEntity selectedRestaurant = ((App) callback).selectedRestaurant;

        setLayout(new BorderLayout());

        // Stworzenie listy dań
        dishListModel = new DefaultListModel<>();
        dishList = new JList<>(dishListModel);
        refreshDishList(selectedRestaurant.getRestaurantId());
        add(new JScrollPane(dishList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        // Dodanie przycisku usuwania dania
        removeButton = new JButton("Usuń danie");
        removeButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, removeButton.getPreferredSize().height));
        removeButton.addActionListener(e -> {
            String selectedDish = dishList.getSelectedValue();
            if (selectedDish != null) {
                int response = JOptionPane.showConfirmDialog(this, "Na pewno chesz usunąć " + selectedDish + "?", "Potwierdź", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    DishesEntity dish = dishesDAO.getDishByName(selectedDish);
                    dishesDAO.removeDish(dish.getDishId());
                    JOptionPane.showMessageDialog(this, "Usunięto danie.");
                    refreshDishList(selectedRestaurant.getRestaurantId());
                }
            }
        });
        buttonPanel.add(removeButton);

        // Dodanie przycisku powrotu
        backButton = new JButton("Powrót");
        backButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, backButton.getPreferredSize().height));
        backButton.addActionListener(e -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Funckja odświeżająca listę dań
    private void refreshDishList(int restaurantId) {
        List<DishesEntity> dishes = dishesDAO.getDishesByRestaurant(restaurantId);
        dishListModel.clear();
        for (DishesEntity dish : dishes) {
            dishListModel.addElement(dish.getName());
        }
    }
}