package pl.foodapp.gui;

import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.restaurants.RestaurantsDAO;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OpinionRestaurantChoicePanel extends JPanel {
    RestaurantsDAO DAO = new RestaurantsDAO();
    List<RestaurantsEntity> restaurants = DAO.getAllRestaurants();
    JList<String> restaurantList;
    DefaultListModel<String> model = new DefaultListModel<>();
    JTextField searchField = new JTextField();
    private JLabel clockLabel;

    public OpinionRestaurantChoicePanel(Callback callback) {

        setLayout(new BorderLayout());
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchList();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                searchList();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                searchList();
            }
        });

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(3, 1));
        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        upperPanel.add(clockLabel);
        timer.start();
        JLabel titleLabel = new JLabel("Select restaurant");
        upperPanel.add(titleLabel);
        upperPanel.add(searchField);
        add(upperPanel, BorderLayout.NORTH);

        model.addAll(restaurants.stream().map(RestaurantsEntity::getName).toList());
        restaurantList = new JList<>(model);

        restaurantList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        restaurantList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && restaurantList.getSelectedValue() != null) {
                    String selected = restaurantList.getSelectedValue();
                    restaurantList.clearSelection();
                    searchField.setText("");
                    RestaurantsDAO DAO = new RestaurantsDAO();
                    callback.onOpinionRestaurantSelected(DAO.getRestaurantByName(selected));
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(restaurantList);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            restaurantList.clearSelection();
            searchField.setText("");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
        });

        add(backButton, BorderLayout.SOUTH);
    }

    private void updateClock() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(format.format(new Date()));
    }

    public void searchList() {
        model.clear();
        String search = searchField.getText();
        for (RestaurantsEntity restaurant : restaurants) {
            if (restaurant.getName().toLowerCase().contains(search.toLowerCase())) {
                model.addElement(restaurant.getName());
            }
        }
    }
}
