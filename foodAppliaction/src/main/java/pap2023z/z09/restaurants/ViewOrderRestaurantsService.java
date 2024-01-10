package pap2023z.z09.restaurants;

import pap2023z.z09.database.OrderedDishesEntity;
import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;

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
