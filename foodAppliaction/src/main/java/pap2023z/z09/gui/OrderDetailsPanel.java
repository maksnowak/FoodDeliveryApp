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

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderDetailsPanel extends JPanel {
    private int accountId;
    OrdersDAO DAO = new OrdersDAO();
    DishesDAO dishesDAO = new DishesDAO();
    OrderedDishesDAO orderedDishesDAO = new OrderedDishesDAO();
    RestaurantsDAO restaurantsDAO = new RestaurantsDAO();
    List<DishesDTO> dishesList;
    JList<String> basketList;
    DefaultListModel<String> model = new DefaultListModel<>();
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

        basketList = new JList<>(model);

        basketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        basketList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && basketList.getSelectedValue() != null) {
                    DishesDTO selectedDish = dishesList.get(basketList.getSelectedIndex());
                    JOptionPane.showMessageDialog(null, "Nazwa: " + selectedDish.getName());
            }
        }});
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

    public void enter(int accountId, int orderId) {
        this.accountId = accountId;
        ViewOrderDetailsService service = new ViewOrderDetailsService(DAO, orderedDishesDAO, dishesDAO);
        dishesList = service.getOrderedDishes(orderId);
        System.out.println(dishesList);
        System.out.println(orderId);
        
        model.clear();
        for (DishesDTO dish : dishesList) {
            model.addElement(dish.getName() + " - " + restaurantsDAO.getRestaurantById(dish.getRestaurantId()).getName());
        }
    }
}
