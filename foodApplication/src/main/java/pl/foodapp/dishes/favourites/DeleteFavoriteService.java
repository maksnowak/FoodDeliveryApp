package pl.foodapp.dishes.favourites;

import pl.foodapp.database.FavoritesEntity;

public class DeleteFavoriteService {
    private final FavoritesDAO favoritesDAO;

    public DeleteFavoriteService(FavoritesDAO favoritesDAO) {
        this.favoritesDAO = favoritesDAO;
    }

    public void deleteFavorite(int id) {
        FavoritesEntity favorite = favoritesDAO.getFavoriteById(id);
        if (favorite != null) {
            favoritesDAO.deleteFavorite(favorite);
        }
    }
}
