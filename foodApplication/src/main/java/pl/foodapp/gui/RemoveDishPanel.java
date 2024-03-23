package pl.foodapp.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import pl.foodapp.database.DishesEntity;
import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.dishes.DishesDAO;

public class RemoveDishPanel extends JPanel {
    private JList<DishesEntity> dishList;
    private JButton removeButton;
    private JButton backButton;
    private DishesDAO dishesDAO;
    private DefaultListModel<DishesEntity> dishListModel;

    public RemoveDishPanel(Callback callback) {
        dishesDAO = new DishesDAO();
        RestaurantsEntity selectedRestaurant = ((App) callback).selectedRestaurant;

        setLayout(new BorderLayout());

        // Add list of dishes
        dishListModel = new DefaultListModel<>();
        dishList = new JList<>(dishListModel);
        dishList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                DishesEntity dish = (DishesEntity) value;
                return super.getListCellRendererComponent(list, dish.getName(), index, isSelected, cellHasFocus);
            }
        });
        refreshDishList(selectedRestaurant.getRestaurantId());
        add(new JScrollPane(dishList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        // Add button to remove dish
        removeButton = new JButton("Remove dish");
        removeButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, removeButton.getPreferredSize().height));
        removeButton.addActionListener(e -> {
            DishesEntity selectedDish = dishList.getSelectedValue();
            if (selectedDish != null) {
                int response = JOptionPane.showConfirmDialog(this, "Do you want to remove " + selectedDish.getName() + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    dishesDAO.removeDish(selectedDish.getDishId());
                    JOptionPane.showMessageDialog(this, "Dish removed.");
                    refreshDishList(selectedRestaurant.getRestaurantId());
                }
            }
        });


        // Add back button
        backButton = new JButton("Back");
        backButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, backButton.getPreferredSize().height));
        backButton.addActionListener(e -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
        });
        buttonPanel.add(backButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void refreshDishList(int restaurantId) {
        List<DishesEntity> dishes = dishesDAO.getDishesByRestaurant(restaurantId);
        dishListModel.clear();
        for (DishesEntity dish : dishes) {
            dishListModel.addElement(dish);
        }
    }
}