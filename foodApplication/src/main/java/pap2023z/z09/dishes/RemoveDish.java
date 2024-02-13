package pap2023z.z09.dishes;

import pap2023z.z09.baskets.BasketsDAO;
import pap2023z.z09.database.BasketsEntity;
import pap2023z.z09.database.FavoritesEntity;
import pap2023z.z09.database.OrderedDishesEntity;
import pap2023z.z09.dishes.favourites.FavoritesDAO;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;

public class RemoveDish {
    private final DishesDAO dishesDAO;
    private final OrderedDishesDAO orderedDishesDAO;
    private final BasketsDAO basketsDAO;
    private final FavoritesDAO favoritesDAO;

    public RemoveDish(DishesDAO dishesDAO, OrderedDishesDAO orderedDishesDAO, BasketsDAO basketsDAO, FavoritesDAO favoritesDAO) {
        this.dishesDAO = dishesDAO;
        this.orderedDishesDAO = orderedDishesDAO;
        this.basketsDAO = basketsDAO;
        this.favoritesDAO = favoritesDAO;
    }

    public void removeDish(int dishId) {
        for (OrderedDishesEntity orderedDishesEntity : orderedDishesDAO.getDishesByDishId(dishId)) {
            orderedDishesDAO.deleteDish(orderedDishesEntity);
        }
        for (BasketsEntity basketsEntity : basketsDAO.getDishesByDishId(dishId)) {
            basketsDAO.deleteBasket(basketsEntity);
        }
        for (FavoritesEntity favoritesEntity : favoritesDAO.getFavoritesByDish(dishId)) {
            favoritesDAO.deleteFavorite(favoritesEntity);
        }
        dishesDAO.removeDish(dishId);
    }
}
