package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;

import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.orders.OrdersDAO;
import pap2023z.z09.dishes.DishesDAO;

public class RestaurantStatisticsPanel extends JPanel {
    private JLabel orderedDishesLabel;
    private JLabel totalIncomeLabel;
    private JButton backButton;
    private OrdersDAO ordersDAO;
    private DishesDAO dishesDAO;

    public RestaurantStatisticsPanel(Callback callback) {
        ordersDAO = new OrdersDAO();
        dishesDAO = new DishesDAO();

        setLayout(new GridLayout(3, 1));

        orderedDishesLabel = new JLabel();
        add(orderedDishesLabel);

        totalIncomeLabel = new JLabel();
        add(totalIncomeLabel);

        backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurants");
        });
        add(backButton);
    }

    public void enter(RestaurantsEntity restaurant) {
//        int orderedDishes = ordersDAO.getNumberOfOrderedDishes(restaurant.getRestaurantId());
        orderedDishesLabel.setText("Liczba zamówionych dań: " );

//        double totalIncome = dishesDAO.getTotalIncome(restaurant.getRestaurantId());
        totalIncomeLabel.setText("Całkowity dochód: ");
    }
}