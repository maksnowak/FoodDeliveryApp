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
    BasketsDAO basketsDAO = new BasketsDAO();
    int accountId;
    List<BasketsEntity> baskets;
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

        JPanel bottomPanel = new JPanel(new GridLayout(2, 2));

        bottomPanel.add(new JLabel());

        JButton deleteButton = new JButton("Usuń danie");
        deleteButton.addActionListener(e -> {
            int selectedId = dishesList.getSelectedIndex();
            if (selectedId != -1) {
                int actualId = baskets.get(selectedId).getDishId();
                BasketsEntity basket = baskets.get(actualId);
                basketsDAO.deleteBasket(basket);
                listBasket();
            }
        });
        bottomPanel.add(deleteButton);

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
        this.accountId = accountId;
        listBasket();
    }

    private void listBasket() {
        System.out.println("listBasket");
        model.clear();
        BasketsDAO basketsDAO = new BasketsDAO();
        DishesDAO dishesDAO = new DishesDAO();
        baskets = basketsDAO.getAllDishesOfClientId(accountId);
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
