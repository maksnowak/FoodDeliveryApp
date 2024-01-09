package pap2023z.z09.orders;

import pap2023z.z09.baskets.AddBasket;
import pap2023z.z09.baskets.BasketsDTO;
import pap2023z.z09.dishes.DishesDTO;

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
        // pobierz z bazdy danych zamówienia dla konta o podanym id
        List<DishesDTO> dishes = viewOrderDetailsService.getOrderedDishes(orderId);
        for (DishesDTO dish : dishes) {
            // dodaj każde z zamówionych dań do koszyka klienta
            BasketsDTO basket = new BasketsDTO();
            basket.setCustomerId(ordersDAO.getOrderById(orderId).getCustomer());
            basket.setDishId(dish.getDishId());
            addBasket.addBasket(basket);
        }
    }
}
