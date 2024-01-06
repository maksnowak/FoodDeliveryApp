package pap2023z.z09.baskets;

import pap2023z.z09.accounts.AccountsDAO;
import pap2023z.z09.database.*;

import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.orderedDishes.OrderedDishesDAO;
import pap2023z.z09.orders.OrdersDAO;


import java.util.ArrayList;
import java.util.List;

public class AddBasket {
    private final BasketsDAO B_DAO;
    private final List<Integer> basketID = new ArrayList<>();
    public AddBasket(BasketsDAO B_DAO) {
        this.B_DAO = B_DAO;
    }
    public void addBasket(BasketsDTO item) {
        checkCustomerId(item.getCustomerID());
        checkDishId(item.getDishId());

        BasketsEntity entity = new BasketsEntity();

        entity.setCustomer(item.getCustomerID());
        entity.setDishId(item.getDishId());

        B_DAO.addBasket(entity);
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
