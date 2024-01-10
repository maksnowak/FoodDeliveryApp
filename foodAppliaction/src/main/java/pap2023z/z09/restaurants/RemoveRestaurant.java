package pap2023z.z09.restaurants;

import pap2023z.z09.baskets.BasketsDAO;
import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.dishes.RemoveDish;
import pap2023z.z09.dishes.favourites.FavoritesDAO;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;

public class RemoveRestaurant {
    private final RestaurantsDAO restaurantsDAO;
    private final DishesDAO dishesDAO;
    private final OrderedDishesDAO orderedDishesDAO;
    private final BasketsDAO basketsDAO;
    private final FavoritesDAO favoritesDAO;

    public RemoveRestaurant(RestaurantsDAO restaurantsDAO, DishesDAO dishesDAO, OrderedDishesDAO orderedDishesDAO, BasketsDAO basketsDAO, FavoritesDAO favoritesDAO) {
        this.restaurantsDAO = restaurantsDAO;
        this.dishesDAO = dishesDAO;
        this.orderedDishesDAO = orderedDishesDAO;
        this.basketsDAO = basketsDAO;
        this.favoritesDAO = favoritesDAO;
    }

    public void removeRestaurant(int restaurantId) {
        RemoveDish removeDish = new RemoveDish(dishesDAO, orderedDishesDAO, basketsDAO, favoritesDAO);
        for (DishesEntity dishesEntity : dishesDAO.getDishesByRestaurant(restaurantId)) {
            removeDish.removeDish(dishesEntity.getDishId());
        }
        restaurantsDAO.removeRestaurant(restaurantId);
    }
}
