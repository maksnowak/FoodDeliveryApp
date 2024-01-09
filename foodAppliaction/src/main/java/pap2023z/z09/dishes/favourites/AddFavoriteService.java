package pap2023z.z09.dishes.favourites;

import pap2023z.z09.database.FavoritesEntity;

import java.util.List;

public class AddFavoriteService {
    private final FavoritesDAO favoritesDAO;

    public AddFavoriteService(FavoritesDAO favoritesDAO) {
        this.favoritesDAO = favoritesDAO;
    }

    public void addFavorite(int dishId, int customerId) {
        List<FavoritesEntity> customerFavorites = favoritesDAO.getFavoritesByCustomer(customerId);
        for (FavoritesEntity favorite : customerFavorites) {
            if (favorite.getDishId() == dishId) {
                throw new IllegalArgumentException("Dish is already in favorites");
            }
        }
        FavoritesEntity newFavorite = new FavoritesEntity();
        newFavorite.setDishId(dishId);
        newFavorite.setCustomer(customerId);
        favoritesDAO.addFavorite(newFavorite);
    }
}
