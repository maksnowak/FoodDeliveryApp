package pap2023z.z09.dishes;

import pap2023z.z09.database.DishesEntity;

import java.math.BigDecimal;

public class DishesDTO {
    private int dishId;
    private String name;
    private int restaurantId;
    private int typeId;
    private Boolean vegetarian;
    private BigDecimal price;
    private BigDecimal kcal;

    public DishesDTO(int dishId, String name, int restaurantId, int typeId, Boolean vegetarian, BigDecimal price, BigDecimal kcal) {
        this.dishId = dishId;
        this.name = name;
        this.restaurantId = restaurantId;
        this.typeId = typeId;
        this.vegetarian = vegetarian;
        this.price = price;
        this.kcal = kcal;
    }

    public DishesDTO() {
    }

    public int getDishId() {
        return dishId;
    }

    public String getName() {
        return name;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getTypeId() {
        return typeId;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getKcal() {
        return kcal;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setKcal(BigDecimal kcal) {
        this.kcal = kcal;
    }

    public static DishesDTO fromEntity(DishesEntity entity) {
        return new DishesDTO(
                entity.getDishId(),
                entity.getName(),
                entity.getRestaurantId(),
                entity.getTypeId(),
                entity.isVegetarian(),
                entity.getPrice(),
                entity.getKcal()
        );
    }
}
