package pl.foodapp.dishes.favourites;

import pl.foodapp.database.FavoritesEntity;

import java.util.List;

public class AddFavoriteService {
    private final FavoritesDAO favoritesDAO;

    public AddFavoriteService(FavoritesDAO favoritesDAO) {
        this.favoritesDAO = favoritesDAO;
    }

    public void addFavorite(int dishId, int customerId) {
        // check if the customer already has this dish in favorites
        List<FavoritesEntity> customerFavorites = favoritesDAO.getFavoritesByCustomer(customerId);
        for (FavoritesEntity favorite : customerFavorites) {
            if (favorite.getDishId() == dishId) {
                // if so, throw an exception
                throw new IllegalArgumentException("Dish is already in favorites");
            }
        }
        // if not, add to favorites
        FavoritesEntity newFavorite = new FavoritesEntity();
        newFavorite.setDishId(dishId);
        newFavorite.setCustomer(customerId);
        favoritesDAO.addFavorite(newFavorite);
    }

    public void addFavorite(FavoritesDTO favourite) {
        // check if the customer already has this dish in favorites
        List<FavoritesEntity> customerFavorites = favoritesDAO.getFavoritesByCustomer(favourite.getCustomer());
        for (FavoritesEntity favorite : customerFavorites) {
            if (favorite.getDishId() == favourite.getDishId()) {
                // if so, throw an exception
                throw new IllegalArgumentException("Dish is already in favorites");
            }
        }
        // if not, add to favorites
        FavoritesEntity newFavorite = new FavoritesEntity();
        newFavorite.setDishId(favourite.getDishId());
        newFavorite.setCustomer(favourite.getCustomer());
        favoritesDAO.addFavorite(newFavorite);
    }
}
