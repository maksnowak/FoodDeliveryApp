package pl.foodapp.gui;

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

import pl.foodapp.database.DishesEntity;
import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.DishesDTO;


public class FavouritesSelectionPanel extends JPanel {
    DishesDAO dishesDAO = new DishesDAO();

    List<DishesEntity> selectedRestaurantsDishes;
    DishListModel model = new DishListModel();
    JList<String> dishList = new JList<>(model);
    boolean isListenerActive = false;
    JLabel titleLabel = new JLabel("Wybierz danie:");
    JTextField searchField = new JTextField();
    JComboBox<String> favouritesTypeComboBox = new JComboBox<>();
    JTextField kcalMinField = new JTextField();
    JTextField kcalMaxField = new JTextField();
    JTextField priceMinField = new JTextField();
    JTextField priceMaxField = new JTextField();
    JCheckBox vegetarianCheckBox = new JCheckBox("wege");
    JComboBox<String> sortComboBox = new JComboBox<>();
    JLabel clockLabel;

    public FavouritesSelectionPanel(Callback callback) {
        setLayout(new BorderLayout());

        favouritesTypeComboBox.addItem("Wszystkie");
        favouritesTypeComboBox.addItem("Przystawka");
        favouritesTypeComboBox.addItem("Zupa");
        favouritesTypeComboBox.addItem("Danie główne");
        favouritesTypeComboBox.addItem("Deser");
        favouritesTypeComboBox.addItem("Dodatki");
        favouritesTypeComboBox.addItem("Sałatki");
        favouritesTypeComboBox.addItem("Napoje");

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
        filterPanelUp.add(favouritesTypeComboBox);
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

        favouritesTypeComboBox.addActionListener(e -> searchAndFilterList());
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
                    List<DishesEntity> filteredList = searchAndFilterList();
                    dishList.clearSelection();

                    DishesDTO dish = DishesDTO.fromEntity(dishesDAO.getDishById(filteredList.get(index).getDishId()));

                    callback.addToFavourites(dish);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(dishList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 1));

        JButton backButton = new JButton("powrot");
        backButton.addActionListener(e -> {
            isListenerActive = false;
            searchField.setText("");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "favouritesRestaurantChoicePanel");
        });
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateClock() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        clockLabel.setText(format.format(new Date()));
    }

    public void enter(RestaurantsEntity restaurant) {
        isListenerActive = true;
        titleLabel.setText("podane dania z restauracji: " + restaurant.getName());
        selectedRestaurantsDishes = dishesDAO.getDishesByRestaurant(restaurant.getRestaurantId());
        searchAndFilterList();
    }

    public List<DishesEntity> searchAndFilterList() {
        model.clear();
        String search = searchField.getText();
        for (DishesEntity dish : selectedRestaurantsDishes) {
            if (dish.getName().toLowerCase().contains(search.toLowerCase()) &&
                    (favouritesTypeComboBox.getSelectedIndex() == 0 || dish.getTypeId() == favouritesTypeComboBox.getSelectedIndex()) &&
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
            dishesList.add(selectedRestaurantsDishes.stream().filter(dish -> dish.getName().equals(model.get(finalI).split(" - ")[0])).findFirst().get());
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
        return dishesList;
    }
}
