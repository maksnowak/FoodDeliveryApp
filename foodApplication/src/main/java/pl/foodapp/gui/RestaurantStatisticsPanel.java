package pl.foodapp.gui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

import pl.foodapp.database.DishesEntity;
import pl.foodapp.orders.OrdersDAO;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDAO;
import pl.foodapp.database.OrderedDishesEntity;

public class RestaurantStatisticsPanel extends JPanel {
    private JLabel orderedDishesLabel;
    private JLabel totalIncomeLabel;
    private JButton backButton;
    private OrdersDAO ordersDAO;
    private DishesDAO dishesDAO;
    private OrderedDishesDAO orderedDishesDAO;


    public RestaurantStatisticsPanel(Callback callback) {
        ordersDAO = new OrdersDAO();
        dishesDAO = new DishesDAO();

        setLayout(new GridLayout(3, 1));

        orderedDishesLabel = new JLabel("Liczba zamówionych dań: ");
        add(orderedDishesLabel);

        totalIncomeLabel = new JLabel("Całkowity dochód: ");
        add(totalIncomeLabel);

        backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurants");
        });
        add(backButton);
    }

    public void enter(int restaurantId) {
        orderedDishesDAO = new OrderedDishesDAO();
        List<OrderedDishesEntity> orderedDishes = orderedDishesDAO.getAllDishes();
        BigDecimal totalIncome = new BigDecimal(0);
        int totalDishes = 0;

        for (OrderedDishesEntity orderedDish : orderedDishes) {
            DishesEntity dish = dishesDAO.getDishById(orderedDish.getDishId());
            if (dish.getRestaurantId() == restaurantId) {
                totalIncome = totalIncome.add(dish.getPrice());
                totalDishes += 1;
            }
        }

        orderedDishesLabel.setText("Liczba zamówionych dań: " + totalDishes);
        totalIncomeLabel.setText("Całkowity dochód: " + totalIncome + " zł");
    }



}