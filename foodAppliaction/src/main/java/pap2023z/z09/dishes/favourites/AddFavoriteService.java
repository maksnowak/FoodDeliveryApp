package pap2023z.z09.dishes.favourites;

import pap2023z.z09.database.FavoritesEntity;

import java.util.List;

public class AddFavoriteService {
    private final FavoritesDAO favoritesDAO;

    public AddFavoriteService(FavoritesDAO favoritesDAO) {
        this.favoritesDAO = favoritesDAO;
    }

    public void addFavorite(int dishId, int customerId) {
        // sprawdź czy dany klient już nie ma tego dania w ulubionych
        List<FavoritesEntity> customerFavorites = favoritesDAO.getFavoritesByCustomer(customerId);
        for (FavoritesEntity favorite : customerFavorites) {
            if (favorite.getDishId() == dishId) {
                // jeśli tak, to rzuć wyjątek
                throw new IllegalArgumentException("Dish is already in favorites");
            }
        }
        // jeśli nie, to dodaj do ulubionych
        FavoritesEntity newFavorite = new FavoritesEntity();
        newFavorite.setDishId(dishId);
        newFavorite.setCustomer(customerId);
        favoritesDAO.addFavorite(newFavorite);
    }

    public void addFavorite(FavoritesDTO favourite) {
        // sprawdź czy dany klient już nie ma tego dania w ulubionych
        List<FavoritesEntity> customerFavorites = favoritesDAO.getFavoritesByCustomer(favourite.getCustomer());
        for (FavoritesEntity favorite : customerFavorites) {
            if (favorite.getDishId() == favourite.getDishId()) {
                // jeśli tak, to rzuć wyjątek
                throw new IllegalArgumentException("Dish is already in favorites");
            }
        }
        // jeśli nie, to dodaj do ulubionych
        FavoritesEntity newFavorite = new FavoritesEntity();
        newFavorite.setDishId(favourite.getDishId());
        newFavorite.setCustomer(favourite.getCustomer());
        favoritesDAO.addFavorite(newFavorite);
    }
}
