package pap2023z.z09.gui;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;

import java.math.BigDecimal;
import java.util.List;

import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.dishes.DishesDTO;
import pap2023z.z09.orders.OrdersDTO;
import pap2023z.z09.orders.OrdersDAO;
import pap2023z.z09.orders.OrderHandler;

class DishListModel extends DefaultListModel<String> {
    public void addElementWithNumber(String name, BigDecimal price, BigDecimal kcal){
        addElement(name + " - " + price + " zł, " + kcal + " kcal");
    }
}

public class DishSelectionPanel extends JPanel {
    DishesDAO DD = new DishesDAO();
    OrdersDAO OD = new OrdersDAO();

    List<DishesEntity> dishes;
    DishListModel model = new DishListModel();
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
    JComboBox<String> sortComboBox = new JComboBox<>();
    JLabel clockLabel = new JLabel();



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

        sortComboBox.addItem("Od najtańszych");
        sortComboBox.addItem("Od najdroższych");
        sortComboBox.addItem("Od najmniej kalorycznych");
        sortComboBox.addItem("Od najbardziej kalorycznych");

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(5, 1));
        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        upperPanel.add(clockLabel);
        timer.start();
        upperPanel.add(titleLabel);
        JPanel searchAndSortPanel = new JPanel();
        searchAndSortPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        searchAndSortPanel.add(new JLabel("Wyszukaj: "), c);
        c.weightx = 15.0;
        searchAndSortPanel.add(searchField, c);
        c.weightx = 1.0;
        searchAndSortPanel.add(new JLabel(), c);
        c.weightx = 1.0;
        searchAndSortPanel.add(new JLabel("Sortuj według:"), c);
        c.weightx = 1.0;
        searchAndSortPanel.add(sortComboBox, c);
        upperPanel.add(searchAndSortPanel);

        JPanel filterPanelUp = new JPanel();
        filterPanelUp.setLayout(new GridLayout(1, 4));
        filterPanelUp.add(typeComboBox);
        filterPanelUp.add(new JLabel("Kalorie: ", SwingConstants.RIGHT));
        filterPanelUp.add(kcalMinField);
        filterPanelUp.add(kcalMaxField);

        JPanel filterPanelDown = new JPanel();
        filterPanelDown.setLayout(new GridLayout(1, 4));
        filterPanelDown.add(vegetarianCheckBox);
        filterPanelDown.add(new JLabel("Cena: ", SwingConstants.RIGHT));
        filterPanelDown.add(priceMinField);
        filterPanelDown.add(priceMaxField);

        upperPanel.add(filterPanelUp);
        upperPanel.add(filterPanelDown);
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
        sortComboBox.addActionListener(e -> searchAndFilterList());

        dishList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dishList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && dishList.getSelectedValue() != null) {
                    int index = dishList.getSelectedIndex();
                    dishList.clearSelection();
                    DishesDTO dish = DishesDTO.fromEntity(DD.getDishById(dishes.get(index).getDishId()));
                    callback.addToBasket(dish);
//                    OrdersDTO order = new OrdersDTO();
//                    OrderHandler orderHandler = new OrderHandler(OD);
//                    //Some of the values hardcoded for now, will be updated
//                    order.setCustomerId(((App) callback).loggedAccount.getAccountId());
//                    order.setTotal(dishes.get(index).getPrice());
//                    order.setPaymentMethodId(1);
//                    order.setStreet("ulica");
//                    order.setStreetNumber(1);
//                    order.setApartment(1);
//                    order.setCity("miasto");
//                    order.setDiscountId(1);
//                    order.setStatusId(1);
//                    order.setTip(new BigDecimal(0));
//                    orderHandler.addOrder(order);
//                    JOptionPane.showMessageDialog(null, "Zamówiono danie: " + selected);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(dishList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            isListenerActive = false;
            searchField.setText("");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "RestaurantChoice");
        });
        bottomPanel.add(backButton);

        JButton basketButton = new JButton("Koszyk");
        basketButton.addActionListener(e -> {
            isListenerActive = false;
            searchField.setText("");
            callback.enterBasket();
        });
        bottomPanel.add(basketButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateClock() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(format.format(new Date()));
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
                    (!vegetarianCheckBox.isSelected() || dish.isVegetarian())) {
                if ((kcalMinField.getText().isEmpty() || dish.getKcal().compareTo(new BigDecimal(kcalMinField.getText())) >= 0) &&
                    (kcalMaxField.getText().isEmpty() || dish.getKcal().compareTo(new BigDecimal(kcalMaxField.getText())) <= 0)) {
                    model.addElementWithNumber(dish.getName(), dish.getPrice(), dish.getKcal());
                }
            }
        }
        List<DishesEntity> dishesList = new ArrayList<>();
        for (int i = 0; i < model.size(); i++) {
            int finalI = i;
            dishesList.add(dishes.stream().filter(dish -> dish.getName().equals(model.get(finalI).split(" - ")[0])).findFirst().get());
        }
        if (sortComboBox.getSelectedIndex() == 0) {
            dishesList.sort(Comparator.comparing(DishesEntity::getPrice));
        }
        else if (sortComboBox.getSelectedIndex() == 1) {
            dishesList.sort(Comparator.comparing(DishesEntity::getPrice).reversed());
        }
        else if (sortComboBox.getSelectedIndex() == 2) {
            dishesList.sort(Comparator.comparing(DishesEntity::getKcal));
        }
        else if (sortComboBox.getSelectedIndex() == 3) {
            dishesList.sort(Comparator.comparing(DishesEntity::getKcal).reversed());
        }
        model.clear();
        for (DishesEntity dish : dishesList) {
            model.addElementWithNumber(dish.getName(), dish.getPrice(), dish.getKcal());
        }
    }
}
