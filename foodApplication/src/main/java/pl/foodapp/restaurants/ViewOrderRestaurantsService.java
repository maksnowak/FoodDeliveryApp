package pl.foodapp.restaurants;

import pl.foodapp.database.OrderedDishesEntity;
import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDAO;

import java.util.ArrayList;
import java.util.List;

public class ViewOrderRestaurantsService {
    private final RestaurantsDAO restaurantsDAO;
    private final DishesDAO dishesDAO;
    private final OrderedDishesDAO orderedDishesDAO;

    public ViewOrderRestaurantsService(RestaurantsDAO restaurantsDAO, DishesDAO dishesDAO, OrderedDishesDAO orderedDishesDAO) {
        this.restaurantsDAO = restaurantsDAO;
        this.dishesDAO = dishesDAO;
        this.orderedDishesDAO = orderedDishesDAO;
    }

    public List<RestaurantsDTO> getRestaurantsFromOrder(int orderId) {
        // wyświetl listę restauracji realizujących zamówienie o podanym id
        List<OrderedDishesEntity> orderedDishes = orderedDishesDAO.getDishesByOrderId(orderId);
        ArrayList<RestaurantsDTO> dtos = new ArrayList<>();
        for (OrderedDishesEntity orderedDish : orderedDishes) {
            boolean inList = false;
            int dishId = orderedDish.getDishId();
            int restaurantId = dishesDAO.getDishById(dishId).getRestaurantId();
            RestaurantsEntity restaurant = restaurantsDAO.getRestaurantById(restaurantId);
            RestaurantsDTO dto = RestaurantsDTO.fromEntity(restaurant);
            for (RestaurantsDTO d : dtos) {
                if (d.getRestaurantId() == dto.getRestaurantId()) {
                    inList = true;
                    break;
                }
            }
            if (!inList) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
