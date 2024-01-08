package pap2023z.z09.orders;

import pap2023z.z09.baskets.AddBasket;
import pap2023z.z09.baskets.BasketsDAO;
import pap2023z.z09.baskets.BasketsDTO;
import pap2023z.z09.database.OrderedDishesEntity;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.dishes.DishesDTO;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;
import pap2023z.z09.dishes.orderedDishes.OrderedDishsesDTO;

import java.util.List;

public class AddOrderDishesToBasketService {
    private final OrdersDAO ordersDAO;
    private final OrderedDishesDAO orderedDishesDAO;
    private final DishesDAO dishesDAO;
    private final BasketsDAO basketsDAO;

    public AddOrderDishesToBasketService(OrdersDAO ordersDAO, OrderedDishesDAO orderedDishesDAO, DishesDAO dishesDAO, BasketsDAO basketsDAO) {
        this.ordersDAO = ordersDAO;
        this.orderedDishesDAO = orderedDishesDAO;
        this.dishesDAO = dishesDAO;
        this.basketsDAO = basketsDAO;
    }

    public void addOrderDishesToBasket(int orderId) {
        ViewOrderDetailsService viewOrderDetailsService = new ViewOrderDetailsService(ordersDAO, orderedDishesDAO, dishesDAO);
        List<DishesDTO> dishes = viewOrderDetailsService.getOrderedDishes(orderId);
        for (DishesDTO dish : dishes) {
            AddBasket addBasket = new AddBasket(basketsDAO);
            BasketsDTO basket = new BasketsDTO();
            basket.setCustomerId(ordersDAO.getOrderById(orderId).getCustomer());
            basket.setDishId(dish.getDishId());
            addBasket.addBasket(basket);
        }
    }
}
