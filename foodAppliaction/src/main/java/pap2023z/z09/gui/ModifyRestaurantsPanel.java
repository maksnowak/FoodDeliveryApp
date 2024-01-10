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
    JList<Integer> restaurantList;
    int accountId;

    JButton removeRestaurantButton = new JButton("Usuń restaurację");
    JButton modifyRestaurantButton = new JButton("Modyfikuj restaurację");
    JButton backButton = new JButton("Powrót");
    JButton addNewRestaurantButton = new JButton("Dodaj restaurację");
    DefaultListModel<Integer> restaurantListModel;

    public ModifyRestaurantsPanel(Callback callback) {
        setLayout(new BorderLayout());

        // Dodanie tytułu
        JLabel titleLabel = new JLabel("Wybierz restaurację do modyfikacji:");
        add(titleLabel);

        restaurantListModel = new DefaultListModel<>();
        restaurantList = new JList<>(restaurantListModel);
        add(new JScrollPane(restaurantList));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));

        // Dodanie przycisku do usuwania restauracji
        removeRestaurantButton.addActionListener(e -> {
            // FIXME: Usuwanie restauracji powinno być po ID, a nie po nazwie
            Integer selectedRestaurantName = restaurantList.getSelectedValue();
            RestaurantsEntity selectedRestaurant = restaurantsDAO.getRestaurantById(selectedRestaurantName);
            RemoveRestaurant removeRestaurant = new RemoveRestaurant(new RestaurantsDAO(), new DishesDAO(), new OrderedDishesDAO(), new BasketsDAO(), new FavoritesDAO(), new WorkersDAO());
            removeRestaurant.removeRestaurant(selectedRestaurant.getRestaurantId());
            refreshRestaurantList();
            JOptionPane.showMessageDialog(this, "Usunięto restaurację.");
            restaurantList.clearSelection();

        });
        buttonPanel.add(removeRestaurantButton);

        // Dodanie przycisku do modyfikacji restauracji
        modifyRestaurantButton.addActionListener(e -> {
            Integer selectedRestaurantName = restaurantList.getSelectedValue();
            if (selectedRestaurantName != null) {
                RestaurantsEntity restaurant = new RestaurantsDAO().getRestaurantById(selectedRestaurantName);
                ((App) callback).selectedRestaurant = restaurant;
                restaurantList.clearSelection();
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
            }
        });
        buttonPanel.add(modifyRestaurantButton);

        // Przycisk powrotu
        backButton.addActionListener(e -> {
            restaurantList.clearSelection();
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");
        });
        buttonPanel.add(backButton);

        // Dodanie przycisku do dodawania restauracji
        addNewRestaurantButton.addActionListener(e -> {
            restaurantList.clearSelection();
            AddNewRestaurantPanel addNewRestaurantPanel = new AddNewRestaurantPanel(new AddRestaurant(new RestaurantsDAO()), callback);
            ((App) callback).add(addNewRestaurantPanel, "AddNewRestaurant");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "AddNewRestaurant");
            refreshRestaurantList();
        });
        buttonPanel.add(addNewRestaurantButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    void refreshRestaurantList() {
        restaurants = viewWorkerRestaurantsService.getWorkerRestaurants(accountId);
        restaurantListModel.clear();
        for (RestaurantsDTO restaurant : restaurants) {
            restaurantListModel.addElement(restaurant.getRestaurantId());
        }
    }

    void enter(int accountId) {
        this.accountId = accountId;
        refreshRestaurantList();
    }
}