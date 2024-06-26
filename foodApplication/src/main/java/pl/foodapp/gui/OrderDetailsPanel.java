package pl.foodapp.gui;

import pl.foodapp.database.OrdersEntity;
import pl.foodapp.database.StatusesEntity;
import pl.foodapp.orders.OrdersDAO;
import pl.foodapp.orders.ViewOrderDetailsService;
import pl.foodapp.orders.AddOrderDishesToBasketService;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.DishesDTO;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDAO;
import pl.foodapp.restaurants.RestaurantsDAO;
import pl.foodapp.restaurants.RestaurantsDTO;
import pl.foodapp.restaurants.ViewOrderRestaurantsService;
import pl.foodapp.statuses.StatusesDAO;
import pl.foodapp.baskets.AddBasket;
import pl.foodapp.baskets.BasketsDAO;

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

    OrdersDAO ordersDAO = new OrdersDAO();
    DishesDAO dishesDAO = new DishesDAO();

    StatusesDAO statusesDAO = new StatusesDAO();
    OrderedDishesDAO orderedDishesDAO = new OrderedDishesDAO();
    RestaurantsDAO restaurantsDAO = new RestaurantsDAO();
    List<RestaurantsDTO> restaurantsList;
    List<DishesDTO> dishesList;
    JList<String> dishesJList;
    JList<String> restaurantsJList;
    DefaultListModel<String> dishesListModel = new DefaultListModel<>();
    DefaultListModel<String> restaurantsListModel = new DefaultListModel<>();
    private JLabel clockLabel;

    private JLabel statusLabel= new JLabel("dia");


    public OrderDetailsPanel(Callback callback) {
        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(3, 1));
        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        upperPanel.add(clockLabel);
        timer.start();
        JLabel titleLabel = new JLabel("Order details:");
        upperPanel.add(titleLabel);

        upperPanel.add(statusLabel);


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

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dishesJList.clearSelection();
            callback.enterHistoryPanel();
        });
        buttonsPanel.add(backButton);

        JButton fastButton = new JButton("Fast order");
        fastButton.addActionListener(e -> {
            dishesJList.clearSelection();
            ViewOrderDetailsService VODservice = new ViewOrderDetailsService(ordersDAO, orderedDishesDAO, dishesDAO);
            AddBasket addBasket = new AddBasket(new BasketsDAO());
            AddOrderDishesToBasketService service = new AddOrderDishesToBasketService(ordersDAO, VODservice, addBasket);
            service.addOrderDishesToBasket(orderId);
            callback.enterBasket();
        });
        buttonsPanel.add(fastButton);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void updateClock() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(format.format(new Date()));
    }

    private String getStatusName(int orderId)
    {
        OrdersEntity order = ordersDAO.getOrderById(orderId);
        StatusesEntity curr_stat = statusesDAO.getStatusById(order.getStatus());
        return(curr_stat.getName());
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

        statusLabel.setText("Current status: " + getStatusName(orderId));
        ViewOrderDetailsService service = new ViewOrderDetailsService(ordersDAO, orderedDishesDAO, dishesDAO);

        dishesList = service.getOrderedDishes(orderId);
        System.out.println(dishesList);
        System.out.println(orderId);

        dishesListModel.clear();

        for (DishesDTO dish : dishesList) {
            dishesListModel.addElement(dish.getName() + " - " + restaurantsDAO.getRestaurantById(dish.getRestaurantId()).getName());
        }
    }
}
