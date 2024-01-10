package pap2023z.z09.gui;

import pap2023z.z09.database.OrdersEntity;
import pap2023z.z09.database.StatusesEntity;
import pap2023z.z09.orders.OrdersDAO;
import pap2023z.z09.orders.ViewOrderDetailsService;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.dishes.DishesDTO;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;
import pap2023z.z09.restaurants.RestaurantsDAO;
import pap2023z.z09.statuses.StatusesDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderDetailsPanel extends JPanel {
    OrdersDAO ordersDAO = new OrdersDAO();
    DishesDAO dishesDAO = new DishesDAO();

    StatusesDAO statusesDAO = new StatusesDAO();
    OrderedDishesDAO orderedDishesDAO = new OrderedDishesDAO();
    RestaurantsDAO restaurantsDAO = new RestaurantsDAO();
    List<DishesDTO> dishesList;
    JList<String> basketList;
    DefaultListModel<String> model = new DefaultListModel<>();
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
        JLabel titleLabel = new JLabel("Szczegóły zamówienia");
        upperPanel.add(titleLabel);

        upperPanel.add(statusLabel);


        add(upperPanel, BorderLayout.NORTH);

        basketList = new JList<>(model);

        basketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        basketList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && basketList.getSelectedValue() != null) {
                    int orderId = Integer.parseInt(basketList.getSelectedValue().split(":")[0]);
                    JOptionPane.showMessageDialog(null, "zamawiańsko zamówiania " + orderId);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(basketList);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            basketList.clearSelection();
            callback.enterHistoryPanel();
        });
        add(backButton, BorderLayout.SOUTH);
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
        statusLabel.setText("aktualny status: " + getStatusName(orderId));
        ViewOrderDetailsService service = new ViewOrderDetailsService(ordersDAO, orderedDishesDAO, dishesDAO);
        dishesList = service.getOrderedDishes(orderId);
        System.out.println(dishesList);
        System.out.println(orderId);

        model.clear();
        for (DishesDTO dish : dishesList) {
            model.addElement(dish.getName() + " - " + restaurantsDAO.getRestaurantById(dish.getRestaurantId()).getName());
        }
    }
}
