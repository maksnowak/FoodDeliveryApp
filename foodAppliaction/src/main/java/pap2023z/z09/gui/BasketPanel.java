package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.restaurants.RestaurantsDAO;
import pap2023z.z09.baskets.BasketsDAO;
import pap2023z.z09.database.BasketsEntity;
import pap2023z.z09.dishes.DishesDAO;

public class BasketPanel extends JPanel {
//    RestaurantsDAO DAO = new RestaurantsDAO();
//    List<RestaurantsEntity> restaurants = DAO.getAllRestaurants(); //orders?
    JList<String> dishesList;
    DefaultListModel<String> model = new DefaultListModel<>();
    private JLabel clockLabel;

    public BasketPanel(Callback callback) {

        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(2, 1));
        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        upperPanel.add(clockLabel);
        timer.start();
        JLabel titleLabel = new JLabel("Twój koszyk:");
        upperPanel.add(titleLabel);
        add(upperPanel, BorderLayout.NORTH);

//        model.addAll(restaurants.stream().map(RestaurantsEntity::getName).toList());
        dishesList = new JList<>(model);

        dishesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        dishesList.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting() && dishesList.getSelectedValue() != null) {
//                    String selected = dishesList.getSelectedValue();
//                    dishesList.clearSelection();
//                    searchField.setText("");
//                    RestaurantsDAO DAO = new RestaurantsDAO();
//                    callback.onRestaurantSelected(DAO.getRestaurantByName(selected));
//                }
//            }
//        });
        JScrollPane scrollPane = new JScrollPane(dishesList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            dishesList.clearSelection();
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
        });
        bottomPanel.add(backButton);

        JButton orderButton = new JButton("Zapłać");
        orderButton.addActionListener(e -> {
            dishesList.clearSelection();
            callback.enterPayment();
        });
        bottomPanel.add(orderButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void enter(int accountId) {
        listBasket(accountId);
    }

    private void listBasket(int accountId) {
        System.out.println("listBasket");
        model.clear();
        BasketsDAO basketsDAO = new BasketsDAO();
        DishesDAO dishesDAO = new DishesDAO();
        List<BasketsEntity> baskets = basketsDAO.getAllDishesOfClientId(accountId);
        System.out.println(baskets.size());
        for (BasketsEntity basket : baskets) {
            DishesEntity dish = dishesDAO.getDishById(basket.getDishId());
            model.addElement(dish.getName());
        }
    }

    private void updateClock() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(format.format(new Date()));
    }
}
