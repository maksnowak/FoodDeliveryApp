package pl.foodapp.orders;

import pl.foodapp.database.DishesEntity;
import pl.foodapp.database.OrderedDishesEntity;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.DishesDTO;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDAO;

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
        // wyświetl szczegóły zamówienia o podanym id
        return OrdersDTO.fromEntity(ordersDAO.getOrderById(orderId));
    }

    public List<DishesDTO> getOrderedDishes(int orderId) {
        // wyświetl listę dań w zamówieniu o podanym id
        List<OrderedDishesEntity> orderedDishes = orderedDishesDAO.getDishesByOrderId(orderId);
        ArrayList<DishesDTO> dtos = new ArrayList<>();
        for (OrderedDishesEntity orderedDish : orderedDishes) {
            DishesEntity dish = dishesDAO.getDishById(orderedDish.getDishId());
            dtos.add(DishesDTO.fromEntity(dish));
        }
        return dtos;
    }
}
