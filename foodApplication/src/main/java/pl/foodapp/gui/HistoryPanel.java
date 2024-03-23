package pl.foodapp.gui;

import pl.foodapp.database.OrdersEntity;
import pl.foodapp.orders.OrdersDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoryPanel extends JPanel {
    private int accountId;
    OrdersDAO DAO = new OrdersDAO();
    List<OrdersEntity> orders;
    JList<String> orderList;
    DefaultListModel<String> model = new DefaultListModel<>();
    private JLabel clockLabel;

    public HistoryPanel(Callback callback) {
        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(2, 1));
        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        upperPanel.add(clockLabel);
        timer.start();
        JLabel titleLabel = new JLabel("Order history:");
        upperPanel.add(titleLabel);
        add(upperPanel, BorderLayout.NORTH);

        orderList = new JList<>(model);

        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && orderList.getSelectedValue() != null) {
                    int orderId = Integer.parseInt(orderList.getSelectedValue().split(":")[0]);
                    callback.enterOrderDetailsPanel(orderId);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(orderList);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            orderList.clearSelection();
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
        });
        add(backButton, BorderLayout.SOUTH);
    }

    private void updateClock() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(format.format(new Date()));
    }

    public void enter(int accountId) {
        this.accountId = accountId;
        orders = DAO.getAllOrdersFromAccountId(accountId);
        model.clear();
        for (OrdersEntity order : orders) {
            model.addElement(order.getOrderId() + ": " + order.getTotal() + " zł");
        }
    }
}
