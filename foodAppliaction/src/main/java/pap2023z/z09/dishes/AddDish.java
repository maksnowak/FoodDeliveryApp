package pap2023z.z09.dishes;

import pap2023z.z09.database.DishesEntity;

public class AddDish {
    private final DishesDAO dishesDAO;


    public AddDish(DishesDAO dishesDAO) {
        this.dishesDAO = dishesDAO;
    }

    public void addDish(DishesDTO dish) {
        DishesEntity entity = new DishesEntity();
        entity.setName(dish.getName());
        entity.setRestaurantId(dish.getRestaurantId());
        entity.setTypeId(dish.getTypeId());
        entity.setVegetarian(dish.getVegetarian());
        entity.setPrice(dish.getPrice());
        entity.setKcal(dish.getKcal());
        dishesDAO.addDish(entity);
    }
}
