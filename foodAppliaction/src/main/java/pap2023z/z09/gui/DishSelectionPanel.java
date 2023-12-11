package pap2023z.z09.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.restaurants.RestaurantsDAO;

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
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAndFilterList();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAndFilterList();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAndFilterList();
            }
        });

        dishList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dishList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && dishList.getSelectedValue() != null) {
                    String selected = dishList.getSelectedValue();
                    dishList.clearSelection();
                    System.out.println("Wybrano danie: " + selected);
                    JOptionPane.showMessageDialog(null, "Wybrano danie: " + selected);
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
        model.clear();
        isListenerActive = true;
        titleLabel.setText("Wybierz danie w restauracji: " + restaurant.getName());
        dishes = DD.getDishesByRestaurant(restaurant.getRestaurantId());
        for (DishesEntity dish : dishes) {
            model.addElement(dish.getName());
        }
    }

    public void searchAndFilterList() {
        model.clear();
        String search = searchField.getText();
        for (DishesEntity dish : dishes) {
            if (dish.getName().toLowerCase().contains(search.toLowerCase())) {
                model.addElement(dish.getName());
            }
        }
    }
}
