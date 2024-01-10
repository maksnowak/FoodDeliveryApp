package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import pap2023z.z09.baskets.BasketsDAO;
import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.dishes.favourites.FavoritesDAO;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;
import pap2023z.z09.restaurants.AddRestaurant;
import pap2023z.z09.restaurants.RemoveRestaurant;
import pap2023z.z09.restaurants.RestaurantsDAO;
import pap2023z.z09.restaurants.RestaurantsDTO;
import pap2023z.z09.workers.ViewWorkerRestaurantsService;
import pap2023z.z09.workers.WorkersDAO;

public class ModifyRestaurantsPanel extends JPanel {
    RestaurantsDAO RD = new RestaurantsDAO();
    WorkersDAO workersDAO = new WorkersDAO();
    RestaurantsDAO restaurantsDAO = new RestaurantsDAO();
    ViewWorkerRestaurantsService viewWorkerRestaurantsService = new ViewWorkerRestaurantsService(workersDAO, restaurantsDAO);
    List<RestaurantsDTO> restaurants;
    JList<String> restaurantNameList;
    JList<Integer> restaurantIdList;
    int accountId;

    JButton removeRestaurantButton = new JButton("Usuń restaurację");
    JButton modifyRestaurantButton = new JButton("Modyfikuj restaurację");
    JButton backButton = new JButton("Powrót");
    JButton addNewRestaurantButton = new JButton("Dodaj restaurację");
    DefaultListModel<String> restaurantNameListModel;
    DefaultListModel<Integer> restaurantIdListModel;
    JButton restaurantStatisticsButton = new JButton("Statystyki restauracji");

    public ModifyRestaurantsPanel(Callback callback) {
        setLayout(new BorderLayout());

        // Dodanie tytułu
        JLabel titleLabel = new JLabel("Wybierz restaurację do modyfikacji:");
        add(titleLabel);

        restaurantNameListModel = new DefaultListModel<>();
        restaurantNameList = new JList<>(restaurantNameListModel);
        add(new JScrollPane(restaurantNameList));

        restaurantIdListModel = new DefaultListModel<>();
        restaurantIdList = new JList<>(restaurantIdListModel);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));

        // Dodanie przycisku do statystyk restauracji

        restaurantStatisticsButton.addActionListener(e -> {
            int selectedRestaurantIndex = restaurantNameList.getSelectedIndex();
            if (selectedRestaurantIndex != -1) {
                Integer selectedRestaurantId = restaurantIdListModel.get(selectedRestaurantIndex);
                RestaurantsEntity restaurant = restaurantsDAO.getRestaurantById(selectedRestaurantId);
                ((App) callback).selectedRestaurant = restaurant;
                restaurantNameList.clearSelection();
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "RestaurantStatistics");
            }
        });
        buttonPanel.add(restaurantStatisticsButton);


        // Dodanie przycisku do usuwania restauracji
        removeRestaurantButton.addActionListener(e -> {
            int selectedRestaurantIndex = restaurantNameList.getSelectedIndex();
            if (selectedRestaurantIndex != -1) {
                Integer selectedRestaurantId = restaurantIdListModel.get(selectedRestaurantIndex);
                System.out.println(selectedRestaurantId);
                System.out.println(selectedRestaurantIndex);
                RemoveRestaurant removeRestaurant = new RemoveRestaurant(new RestaurantsDAO(), new DishesDAO(),  new OrderedDishesDAO(), new BasketsDAO(), new FavoritesDAO(), new WorkersDAO());
                removeRestaurant.removeRestaurant(selectedRestaurantId);
                refreshRestaurantList();
                JOptionPane.showMessageDialog(this, "Usunięto restaurację.");
                restaurantNameList.clearSelection();
            }
        });
        buttonPanel.add(removeRestaurantButton);

        // Dodanie przycisku do modyfikacji restauracji
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



        // Dodanie przycisku do dodawania restauracji
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