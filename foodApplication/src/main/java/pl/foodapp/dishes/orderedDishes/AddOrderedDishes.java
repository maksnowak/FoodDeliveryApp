package pl.foodapp.dishes.orderedDishes;

import pl.foodapp.accounts.AccountsDAO;
import pl.foodapp.baskets.BasketsDAO;
import pl.foodapp.database.*;
import pl.foodapp.database.*;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.orders.OrdersDAO;

import java.util.List;

public class AddOrderedDishes {

    private final OrderedDishesDAO OD_DAO;
    private final BasketsDAO B_DAO;
    private final int accountId;
    
    
    public AddOrderedDishes(OrderedDishesDAO OD_DAO, BasketsDAO B_DAO, int accountId)
    {
        checkCustomerId(accountId);
        this.OD_DAO = OD_DAO;
        this.B_DAO = B_DAO;
        this.accountId = accountId;
    }
    
    public void addOrderedDishes(int orderID)
    {
        List<BasketsEntity> items = B_DAO.getAllDishesOfClientId(accountId);

        OrderedDishesDTO dish;
        for(BasketsEntity item : items)
        {
            dish = new OrderedDishesDTO();
            dish.setDishId(item.getDishId());
            dish.setOrderId(orderID);

            addOrderedDish(dish);
        }
        cleanBasket();
    }

    public void addOrderedDish(OrderedDishesDTO dish)
    {
        checkOrderId(dish.getOrderId());
        checkDishId(dish.getDishId());

        OrderedDishesEntity entity = new OrderedDishesEntity();

        entity.setOrderId(dish.getOrderId());
        entity.setDishId(dish.getDishId());

        OD_DAO.addDish(entity);
    }

    private void cleanBasket()
    {
        List<BasketsEntity> items = B_DAO.getAllDishesOfClientId(accountId);

        for(BasketsEntity item : items)
        {
            B_DAO.deleteBasket(item);
        }
    }

    public void checkOrderId(int id){
        OrdersDAO ordersDAO = new OrdersDAO();
        List <OrdersEntity> orders = ordersDAO.getAllOrders();
        for(OrdersEntity order : orders){
            if(order.getOrderId() == id){
                return;
            }
        }
        throw new IllegalArgumentException("the foreign key CustomerId does not exist in primary keys ");
    }
    public void checkCustomerId(int id){
        AccountsDAO accountsDAO = new AccountsDAO();
        List <AccountsEntity> accounts = accountsDAO.getAllAccounts();
        for(AccountsEntity account :accounts){
            if(account.getAccountId() == id){
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