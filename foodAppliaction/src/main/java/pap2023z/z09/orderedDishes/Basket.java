package pap2023z.z09.orderedDishes;

import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.database.OrdersEntity;

import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.orders.OrdersDAO;

import pap2023z.z09.database.OrderedDishesEntity;


import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final OrderedDishesDAO OD_DAO;
    private final List<Integer> orderedDishesID = new ArrayList<>();
    int orderId;
    public Basket(int orderId, OrderedDishesDAO OD_DAO) {
        this.OD_DAO = OD_DAO;
        checkOrderId(orderId);
        this.orderId = orderId;
    }
    public void addItem(int dishId) {
        checkDishId(dishId);
        orderedDishesID.add(dishId);
    }

    public List<Integer> getAllDishes() {
        return orderedDishesID;
    }

    public void removeByIndex(int i) {
        orderedDishesID.remove(i);
    }

    public void orderReady() {
        OrderedDishesEntity dish;
        for(int dishID : orderedDishesID) {
            dish = new OrderedDishesEntity();
            dish.setOrderId(orderId);
            dish.setDishId(dishID);

            OD_DAO.addDish(dish);
        }
    }

    public void checkOrderId(int id){
        OrdersDAO ordersDAO = new OrdersDAO();
        List <OrdersEntity> orders = ordersDAO.getAllOrders();
        for(OrdersEntity order :orders){
            if(order.getOrderId() == id){
                return;
            }
        }
        throw new IllegalArgumentException("the foreign key CustomerId does not exist in primary keys ");
    }

    public void checkDishId(int id){
        DishesDAO dishesDAO = new DishesDAO();
        List <DishesEntity> dishes = dishesDAO.getAllDishes();
        for(DishesEntity dish :dishes){
            if(dish.getDishId() == id){
                return;
            }
        }
        throw new IllegalArgumentException("the foreign key CustomerId does not exist in primary keys ");
    }
}
