package pl.foodapp.dishes.favourites;

import pl.foodapp.database.FavoritesEntity;
import pl.foodapp.database.DishesEntity;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.DishesDTO;

import java.util.ArrayList;
import java.util.List;


public class FavoriteDishService {
    private final FavoritesDAO favoritesDAO;
    private final DishesDAO dishesDAO;

    public FavoriteDishService(FavoritesDAO favoritesDAO, DishesDAO dishesDAO) {
        this.favoritesDAO = favoritesDAO;
        this.dishesDAO = dishesDAO;
    }

    public DishesDTO getFavoriteDish(int id) {
        FavoritesEntity favorite = favoritesDAO.getFavoriteById(id);
        if (favorite == null) {
            return null;
        }
        DishesEntity dish = dishesDAO.getDishById(favorite.getDishId());
        return DishesDTO.fromEntity(dish);
    }

    public List<FavoritesDTO> getAllCustomerFavorites(int customer) {
        List<FavoritesEntity> favorite = favoritesDAO.getFavoritesByCustomer(customer);
        ArrayList<FavoritesDTO> dtos = new ArrayList<>();
        for (FavoritesEntity favoritesEntity : favorite) {
            dtos.add(FavoritesDTO.fromEntity(favoritesEntity));
        }
        return dtos;
    }
}
