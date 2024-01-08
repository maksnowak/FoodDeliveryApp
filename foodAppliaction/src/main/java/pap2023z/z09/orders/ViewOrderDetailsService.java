package pap2023z.z09.orders;

import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.database.OrderedDishesEntity;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.dishes.DishesDTO;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;

import java.util.ArrayList;
import java.util.List;

public class ViewOrderDetailsService {
    private final OrdersDAO ordersDAO;
    private final OrderedDishesDAO orderedDishesDAO;
    private final DishesDAO dishesDAO;

    public ViewOrderDetailsService(OrdersDAO ordersDAO, OrderedDishesDAO orderedDishesDAO, DishesDAO dishesDAO) {
        this.ordersDAO = ordersDAO;
        this.orderedDishesDAO = orderedDishesDAO;
        this.dishesDAO = dishesDAO;
    }

    public OrdersDTO getOrderDetails(int orderId) {
        return OrdersDTO.fromEntity(ordersDAO.getOrderById(orderId));
    }

    public List<DishesDTO> getOrderedDishes(int orderId) {
        List<OrderedDishesEntity> orderedDishes = orderedDishesDAO.getDishesByOrderId(orderId);
        ArrayList<DishesDTO> dtos = new ArrayList<>();
        for (OrderedDishesEntity orderedDish : orderedDishes) {
            DishesEntity dish = dishesDAO.getDishById(orderedDish.getDishId());
            dtos.add(DishesDTO.fromEntity(dish));
        }
        return dtos;
    }
}
