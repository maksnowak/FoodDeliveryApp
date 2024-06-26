package pl.foodapp.dishes.favourites;

import pl.foodapp.database.FavoritesEntity;

public class FavoritesDTO {
    int id;
    int dishId;
    int customer;

    public FavoritesDTO(int id, int dishId, int customer) {
        this.id = id;
        this.dishId = dishId;
        this.customer = customer;
    }

    public FavoritesDTO() {
    }

    public int getId() {
        return id;
    }

    public int getDishId() {
        return dishId;
    }

    public int getCustomer() {
        return customer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public static FavoritesDTO fromEntity(FavoritesEntity favoritesEntity) {
        return new FavoritesDTO(
                favoritesEntity.getId(),
                favoritesEntity.getDishId(),
                favoritesEntity.getCustomer()
        );
    }
}
