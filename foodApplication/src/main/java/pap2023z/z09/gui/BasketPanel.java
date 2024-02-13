package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.baskets.BasketsDAO;
import pap2023z.z09.database.BasketsEntity;
import pap2023z.z09.dishes.DishesDAO;

public class BasketPanel extends JPanel {
    BasketsDAO basketsDAO = new BasketsDAO();
    int accountId;
    List<BasketsEntity> baskets;
    JList<String> basketsJList;
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

        basketsJList = new JList<>(model);
        basketsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(basketsJList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 2));

        bottomPanel.add(new JLabel());

        JButton deleteButton = new JButton("Usuń danie");
        deleteButton.addActionListener(e -> {
            int selectedId = basketsJList.getSelectedIndex();
            if (selectedId != -1) {
                int actualId = baskets.get(selectedId).getId();
                BasketsEntity basket = basketsDAO.getDishByBasketId(actualId);
                basketsDAO.deleteBasket(basket);
                listBasket();
                if (baskets.isEmpty()) {
                    basketsJList.clearSelection();
                } else if (selectedId == baskets.size()) {
                    basketsJList.setSelectedIndices(new int[] {selectedId - 1});
                } else {
                    basketsJList.setSelectedIndices(new int[] {selectedId});
                }
            }
        });
        bottomPanel.add(deleteButton);

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            basketsJList.clearSelection();
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
        });
        bottomPanel.add(backButton);

        JButton orderButton = new JButton("Zapłać");
        orderButton.addActionListener(e -> {
            basketsJList.clearSelection();
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
        model.clear();
        BasketsDAO basketsDAO = new BasketsDAO();
        DishesDAO dishesDAO = new DishesDAO();
        baskets = basketsDAO.getAllDishesOfClientId(accountId);
        for (BasketsEntity basket : baskets) {
            DishesEntity dish = dishesDAO.getDishById(basket.getDishId()); // dish tylko dla nazwy
            model.addElement(dish.getName());
        }
    }

    private void updateClock() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(format.format(new Date()));
    }
}
