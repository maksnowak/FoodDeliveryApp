package pap2023z.z09.workers;

import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.database.WorkersEntity;
import pap2023z.z09.restaurants.RestaurantsDAO;
import pap2023z.z09.restaurants.RestaurantsDTO;

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
