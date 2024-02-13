package pl.foodapp.dishes;

import pl.foodapp.baskets.BasketsDAO;
import pl.foodapp.database.BasketsEntity;
import pl.foodapp.database.FavoritesEntity;
import pl.foodapp.database.OrderedDishesEntity;
import pl.foodapp.dishes.favourites.FavoritesDAO;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDAO;

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
