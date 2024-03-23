package pl.foodapp.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import pl.foodapp.baskets.BasketsDAO;
import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.favourites.FavoritesDAO;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDAO;
import pl.foodapp.restaurants.AddRestaurant;
import pl.foodapp.restaurants.RemoveRestaurant;
import pl.foodapp.restaurants.RestaurantsDAO;
import pl.foodapp.restaurants.RestaurantsDTO;
import pl.foodapp.reviews.ReviewsDAO;
import pl.foodapp.workers.ViewWorkerRestaurantsService;
import pl.foodapp.workers.WorkersDAO;

public class ModifyRestaurantsPanel extends JPanel {
    RestaurantsDAO RD = new RestaurantsDAO();
    WorkersDAO workersDAO = new WorkersDAO();
    RestaurantsDAO restaurantsDAO = new RestaurantsDAO();
    ViewWorkerRestaurantsService viewWorkerRestaurantsService = new ViewWorkerRestaurantsService(workersDAO, restaurantsDAO);
    List<RestaurantsDTO> restaurants;
    JList<String> restaurantNameList;
    JList<Integer> restaurantIdList;
    int accountId;

    JButton removeRestaurantButton = new JButton("Remove restaurant");
    JButton modifyRestaurantButton = new JButton("Modify restaurant");
    JButton backButton = new JButton("Back");
    JButton addNewRestaurantButton = new JButton("Add new restaurant");
    DefaultListModel<String> restaurantNameListModel;
    DefaultListModel<Integer> restaurantIdListModel;
    JButton restaurantStatisticsButton = new JButton("Restaurant statistics");

    public ModifyRestaurantsPanel(Callback callback) {
        setLayout(new BorderLayout());

        // Add title
        JLabel titleLabel = new JLabel("Modify restaurants");
        add(titleLabel);

        restaurantNameListModel = new DefaultListModel<>();
        restaurantNameList = new JList<>(restaurantNameListModel);
        add(new JScrollPane(restaurantNameList));

        restaurantIdListModel = new DefaultListModel<>();
        restaurantIdList = new JList<>(restaurantIdListModel);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));

        // Add button to show restaurant statistics

        restaurantStatisticsButton.addActionListener(e -> {
            int selectedRestaurantIndex = restaurantNameList.getSelectedIndex();
            if (selectedRestaurantIndex != -1) {
                Integer selectedRestaurantId = restaurantIdListModel.get(selectedRestaurantIndex);
                RestaurantsEntity restaurant = restaurantsDAO.getRestaurantById(selectedRestaurantId);
                ((App) callback).selectedRestaurant = restaurant;
                restaurantNameList.clearSelection();
                callback.enterRestaurantStats(restaurant.getRestaurantId());
            }
        });
        buttonPanel.add(restaurantStatisticsButton);


        // Remove restaurant button
        removeRestaurantButton.addActionListener(e -> {
            int selectedRestaurantIndex = restaurantNameList.getSelectedIndex();
            if (selectedRestaurantIndex != -1) {
                Integer selectedRestaurantId = restaurantIdListModel.get(selectedRestaurantIndex);
                RemoveRestaurant removeRestaurant = new RemoveRestaurant(new RestaurantsDAO(), new DishesDAO(),  new OrderedDishesDAO(), new BasketsDAO(), new FavoritesDAO(), new WorkersDAO(), new ReviewsDAO());
                removeRestaurant.removeRestaurant(selectedRestaurantId);
                refreshRestaurantList();
                JOptionPane.showMessageDialog(this, "Restaurant removed");
                restaurantNameList.clearSelection();
            }
        });
        buttonPanel.add(removeRestaurantButton);

        // Modify restaurant button
        modifyRestaurantButton.addActionListener(e -> {
            int selectedRestaurantIndex = restaurantNameList.getSelectedIndex();
            if (selectedRestaurantIndex != -1) {
                Integer selectedRestaurantId = restaurantIdListModel.get(selectedRestaurantIndex);
                RestaurantsEntity restaurant = restaurantsDAO.getRestaurantById(selectedRestaurantId);
                ((App) callback).selectedRestaurant = restaurant;
                restaurantNameList.clearSelection();
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
            }
        });
        buttonPanel.add(modifyRestaurantButton);


        // Add new restaurant button
        addNewRestaurantButton.addActionListener(e -> {
            restaurantNameList.clearSelection();
            AddNewRestaurantPanel addNewRestaurantPanel = new AddNewRestaurantPanel(new AddRestaurant(new RestaurantsDAO()), callback);
            ((App) callback).add(addNewRestaurantPanel, "AddNewRestaurant");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "AddNewRestaurant");
            refreshRestaurantList();
        });
        buttonPanel.add(addNewRestaurantButton);

        // Przycisk powrotu
        backButton.addActionListener(e -> {
            restaurantNameList.clearSelection();
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    void refreshRestaurantList() {
        restaurants = viewWorkerRestaurantsService.getWorkerRestaurants(accountId);
        restaurantNameListModel.clear();
        restaurantIdListModel.clear();
        for (RestaurantsDTO restaurant : restaurants) {
            restaurantNameListModel.addElement(restaurant.getName());
            restaurantIdListModel.addElement(restaurant.getRestaurantId());
        }
    }

    void enter(int accountId) {
        this.accountId = accountId;
        refreshRestaurantList();
    }
}