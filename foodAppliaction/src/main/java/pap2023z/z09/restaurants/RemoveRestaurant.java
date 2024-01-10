package pap2023z.z09.restaurants;

import pap2023z.z09.baskets.BasketsDAO;
import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.database.WorkersEntity;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.dishes.RemoveDish;
import pap2023z.z09.dishes.favourites.FavoritesDAO;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;
import pap2023z.z09.workers.WorkersDAO;

public class RemoveRestaurant {
    private final RestaurantsDAO restaurantsDAO;
    private final DishesDAO dishesDAO;
    private final OrderedDishesDAO orderedDishesDAO;
    private final BasketsDAO basketsDAO;
    private final FavoritesDAO favoritesDAO;
    private final WorkersDAO workersDAO;

    public RemoveRestaurant(RestaurantsDAO restaurantsDAO, DishesDAO dishesDAO, OrderedDishesDAO orderedDishesDAO, BasketsDAO basketsDAO, FavoritesDAO favoritesDAO, WorkersDAO workersDAO) {
        this.restaurantsDAO = restaurantsDAO;
        this.dishesDAO = dishesDAO;
        this.orderedDishesDAO = orderedDishesDAO;
        this.basketsDAO = basketsDAO;
        this.favoritesDAO = favoritesDAO;
        this.workersDAO = workersDAO;
    }

    public void removeRestaurant(int restaurantId) {
        RemoveDish removeDish = new RemoveDish(dishesDAO, orderedDishesDAO, basketsDAO, favoritesDAO);
        for (DishesEntity dishesEntity : dishesDAO.getDishesByRestaurant(restaurantId)) {
            removeDish.removeDish(dishesEntity.getDishId());
        }
        for (WorkersEntity workersEntity : workersDAO.getWorkersByRestaurantId(restaurantId)) {
            workersDAO.deleteWorker(workersEntity.getId());
        }
        restaurantsDAO.removeRestaurant(restaurantId);
    }
}
