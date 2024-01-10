package pap2023z.z09.gui;

import pap2023z.z09.database.BasketsEntity;
import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.orders.OrdersDAO;
import pap2023z.z09.orders.OrdersDTO;
import pap2023z.z09.orders.ViewOrderDetailsService;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.dishes.DishesDTO;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;
import pap2023z.z09.restaurants.RestaurantsDAO;
import pap2023z.z09.restaurants.RestaurantsDTO;
import pap2023z.z09.restaurants.ViewOrderRestaurantsService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderDetailsPanel extends JPanel {
    private int accountId;
    private int orderId;
    OrdersDAO DAO = new OrdersDAO();
    DishesDAO dishesDAO = new DishesDAO();
    OrderedDishesDAO orderedDishesDAO = new OrderedDishesDAO();
    RestaurantsDAO restaurantsDAO = new RestaurantsDAO();
    List<RestaurantsDTO> restaurantsList;
    List<DishesDTO> dishesList;
    JList<String> dishesJList;
    JList<String> restaurantsJList;
    DefaultListModel<String> dishesListModel = new DefaultListModel<>();
    DefaultListModel<String> restaurantsListModel = new DefaultListModel<>();
    private JLabel clockLabel;

    public OrderDetailsPanel(Callback callback) {
        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(2, 1));
        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        upperPanel.add(clockLabel);
        timer.start();
        JLabel titleLabel = new JLabel("Szczegóły zamówienia");
        upperPanel.add(titleLabel);
        add(upperPanel, BorderLayout.NORTH);

        dishesJList = new JList<>(dishesListModel);
        restaurantsJList = new JList<>(restaurantsListModel);

        JPanel listsPanel = new JPanel(new GridLayout(2, 1));

        dishesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dishesJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                dishesJList.clearSelection();
        }});
        JScrollPane scrollPane = new JScrollPane(dishesJList);
        listsPanel.add(scrollPane, BorderLayout.CENTER);

        restaurantsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        restaurantsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && restaurantsJList.getSelectedValue() != null) {
                    RestaurantsDTO selectedRestaurant = restaurantsList.get(restaurantsJList.getSelectedIndex());
                    restaurantsJList.clearSelection();
                    callback.enterComplaintPanel(orderId);
                }
            }
        });

        JScrollPane scrollPane2 = new JScrollPane(restaurantsJList);
        listsPanel.add(scrollPane2, BorderLayout.EAST);

        add(listsPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            dishesJList.clearSelection();
            callback.enterHistoryPanel();
        });
        add(backButton, BorderLayout.SOUTH);
    }

    private void updateClock() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(format.format(new Date()));
    }

    public void enter(int accountId, int orderId) {
        this.accountId = accountId;
        this.orderId = orderId;

        ViewOrderRestaurantsService viewOrderRestaurantsService = new ViewOrderRestaurantsService(restaurantsDAO, dishesDAO, orderedDishesDAO);
        restaurantsList = viewOrderRestaurantsService.getRestaurantsFromOrder(orderId);

        restaurantsListModel.clear();
        for (RestaurantsDTO restaurant : restaurantsList) {
            restaurantsListModel.addElement(restaurant.getName());
        }

        ViewOrderDetailsService service = new ViewOrderDetailsService(DAO, orderedDishesDAO, dishesDAO);
        dishesList = service.getOrderedDishes(orderId);
        System.out.println(dishesList);
        System.out.println(orderId);

        dishesListModel.clear();
        for (DishesDTO dish : dishesList) {
            dishesListModel.addElement(dish.getName() + " - " + restaurantsDAO.getRestaurantById(dish.getRestaurantId()).getName());
        }
    }
}
