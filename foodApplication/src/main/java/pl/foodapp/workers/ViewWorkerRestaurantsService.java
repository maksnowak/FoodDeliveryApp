package pl.foodapp.workers;

import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.database.WorkersEntity;
import pl.foodapp.restaurants.RestaurantsDAO;
import pl.foodapp.restaurants.RestaurantsDTO;

import java.util.ArrayList;
import java.util.List;

public class ViewWorkerRestaurantsService {
    private final WorkersDAO workersDAO;
    private final RestaurantsDAO restaurantsDAO;

    public ViewWorkerRestaurantsService(WorkersDAO workersDAO, RestaurantsDAO restaurantsDAO) {
        this.workersDAO = workersDAO;
        this.restaurantsDAO = restaurantsDAO;
    }

    public List<RestaurantsDTO> getWorkerRestaurants(int accountId) {
        ArrayList<RestaurantsDTO> restaurants = new ArrayList<>();
        List<WorkersEntity> workerRestaurants = workersDAO.getWorkersByAccountId(accountId);
        for (WorkersEntity workerRestaurant : workerRestaurants) {
            RestaurantsEntity restaurant = restaurantsDAO.getRestaurantById(workerRestaurant.getRestaurantId());
            RestaurantsDTO dto = RestaurantsDTO.fromEntity(restaurant);
            restaurants.add(dto);
        }
        return restaurants;
    }
}
