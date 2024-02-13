package pl.foodapp.dishes;

import pl.foodapp.database.DishesEntity;

public class AddDish {
    private final DishesDAO dishesDAO;


    public AddDish(DishesDAO dishesDAO) {
        this.dishesDAO = dishesDAO;
    }

    public int addDish(DishesDTO dish) {
        DishesEntity entity = new DishesEntity();
        entity.setName(dish.getName());
        entity.setRestaurantId(dish.getRestaurantId());
        entity.setTypeId(dish.getTypeId());
        entity.setVegetarian(dish.getVegetarian());
        entity.setPrice(dish.getPrice());
        entity.setKcal(dish.getKcal());
        return dishesDAO.addDish(entity);
    }
}
