package pap2023z.z09.dishes;

public class RemoveDish {
    private final DishesDAO dishesDAO;

    public RemoveDish(DishesDAO dishesDAO) {
        this.dishesDAO = dishesDAO;
    }

    public void removeDish(int dishId) {
        dishesDAO.removeDish(dishId);
    }
}
