package pl.foodapp.orders;

import pl.foodapp.baskets.AddBasket;
import pl.foodapp.baskets.BasketsDTO;
import pl.foodapp.dishes.DishesDTO;

import java.util.List;

public class AddOrderDishesToBasketService {
    private final OrdersDAO ordersDAO;
    private final ViewOrderDetailsService viewOrderDetailsService;
    private final AddBasket addBasket;

    public AddOrderDishesToBasketService(OrdersDAO ordersDAO, ViewOrderDetailsService viewOrderDetailsService, AddBasket addBasket) {
        this.ordersDAO = ordersDAO;
        this.viewOrderDetailsService = viewOrderDetailsService;
        this.addBasket = addBasket;
    }

    public void addOrderDishesToBasket(int orderId) {
        // get ordered dishes for account with given id from database
        List<DishesDTO> dishes = viewOrderDetailsService.getOrderedDishes(orderId);
        for (DishesDTO dish : dishes) {
            // add each ordered dish to customer's basket
            BasketsDTO basket = new BasketsDTO();
            basket.setCustomerId(ordersDAO.getOrderById(orderId).getCustomer());
            basket.setDishId(dish.getDishId());
            addBasket.addBasket(basket);
        }
    }
}
