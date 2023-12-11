package pap2023z.z09.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

import java.math.BigDecimal;

import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.orders.OrdersDTO;
import pap2023z.z09.orders.AddOrder;

public class DishSelectionPanel extends JPanel {
    DishesDAO DD = new DishesDAO();
    List<DishesEntity> dishes;
    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> dishList = new JList<>(model);
    boolean isListenerActive = false;
    JLabel titleLabel = new JLabel("Wybierz danie:");
    JTextField searchField = new JTextField();
    JComboBox<String> typeComboBox = new JComboBox<>();
    JTextField kcalMinField = new JTextField();
    JTextField kcalMaxField = new JTextField();
    JTextField priceMinField = new JTextField();
    JTextField priceMaxField = new JTextField();
    JCheckBox vegetarianCheckBox = new JCheckBox("wege");


    public DishSelectionPanel(Callback callback) {
        setLayout(new BorderLayout());

        typeComboBox.addItem("Wszystkie");
        typeComboBox.addItem("Przystawka");
        typeComboBox.addItem("Zupa");
        typeComboBox.addItem("Danie główne");
        typeComboBox.addItem("Deser");
        typeComboBox.addItem("Dodatki");
        typeComboBox.addItem("Sałatki");
        typeComboBox.addItem("Napoje");

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(2, 1));
        JPanel upperHalf = new JPanel();
        upperHalf.setLayout(new GridLayout(2, 1));
        upperHalf.add(titleLabel);
        upperHalf.add(searchField);
        upperPanel.add(upperHalf);
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(2, 4));
        filterPanel.add(typeComboBox);
        filterPanel.add(new JLabel("Kalorie:"));
        filterPanel.add(kcalMinField);
        filterPanel.add(kcalMaxField);
        filterPanel.add(vegetarianCheckBox);
        filterPanel.add(new JLabel("Cena:"));
        filterPanel.add(priceMinField);
        filterPanel.add(priceMaxField);

        upperPanel.add(filterPanel);
        add(upperPanel, BorderLayout.NORTH);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { searchAndFilterList(); }
            @Override public void removeUpdate(DocumentEvent e) { searchAndFilterList(); }
            @Override public void changedUpdate(DocumentEvent e) { searchAndFilterList(); }
        });

        typeComboBox.addActionListener(e -> searchAndFilterList());
        kcalMinField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { searchAndFilterList(); }
            @Override public void removeUpdate(DocumentEvent e) { searchAndFilterList(); }
            @Override public void changedUpdate(DocumentEvent e) { searchAndFilterList(); }
        });
        kcalMaxField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { searchAndFilterList(); }
            @Override public void removeUpdate(DocumentEvent e) { searchAndFilterList(); }
            @Override public void changedUpdate(DocumentEvent e) { searchAndFilterList(); }
        });
        priceMinField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { searchAndFilterList(); }
            @Override public void removeUpdate(DocumentEvent e) { searchAndFilterList(); }
            @Override public void changedUpdate(DocumentEvent e) { searchAndFilterList(); }
        });
        priceMaxField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { searchAndFilterList(); }
            @Override public void removeUpdate(DocumentEvent e) { searchAndFilterList(); }
            @Override public void changedUpdate(DocumentEvent e) { searchAndFilterList(); }
        });
        vegetarianCheckBox.addActionListener(e -> searchAndFilterList());

        dishList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dishList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && dishList.getSelectedValue() != null) {
                    String selected = dishList.getSelectedValue();
                    int index = dishList.getSelectedIndex();
                    dishList.clearSelection();
                    OrdersDTO order = new OrdersDTO();
                    AddOrder addOrder = new AddOrder();
                    order.setCustomerId(((App) callback).loggedAccount.getAccountId());
                    order.setTotal(dishes.get(index).getPrice());
                    order.setPaymentMethodId(1);
                    order.setStreet("ulica");
                    order.setStreetNumber(1);
                    order.setApartment(1);
                    order.setCity("miasto");
                    order.setDiscountId(1);
                    order.setStatusId(1);
                    order.setTip(new BigDecimal(0));
                    addOrder.addOrder(order);
                    JOptionPane.showMessageDialog(null, "Zamówiono danie: " + selected);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(dishList);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            isListenerActive = false;
            searchField.setText("");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "RestaurantChoice");
        });
        add(backButton, BorderLayout.SOUTH);
    }

    public void enter(RestaurantsEntity restaurant) {
        isListenerActive = true;
        titleLabel.setText("Wybierz danie w restauracji: " + restaurant.getName());
        dishes = DD.getDishesByRestaurant(restaurant.getRestaurantId());
        searchAndFilterList();
    }

    public void searchAndFilterList() {
        model.clear();
        String search = searchField.getText();
        for (DishesEntity dish : dishes) {
            if (dish.getName().toLowerCase().contains(search.toLowerCase()) &&
                    (typeComboBox.getSelectedIndex() == 0 || dish.getTypeId() == typeComboBox.getSelectedIndex()) &&
                    (priceMinField.getText().isEmpty() || dish.getPrice().compareTo(new BigDecimal(priceMinField.getText())) >= 0) &&
                    (priceMaxField.getText().isEmpty() || dish.getPrice().compareTo(new BigDecimal(priceMaxField.getText())) <= 0) &&
                    (!vegetarianCheckBox.isSelected() || dish.getVegetarian() == null || dish.getVegetarian())) {

                if (dish.getKcal() == null) {
                    model.addElement(dish.getName());
                }
                else if ((kcalMinField.getText().isEmpty() || dish.getKcal().compareTo(new BigDecimal(kcalMinField.getText())) >= 0) &&
                        (kcalMaxField.getText().isEmpty() || dish.getKcal().compareTo(new BigDecimal(kcalMaxField.getText())) <= 0)) {
                    model.addElement(dish.getName());
                }
            }
        }
    }
}
