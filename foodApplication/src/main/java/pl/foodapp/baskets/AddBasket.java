package pl.foodapp.baskets;

import pl.foodapp.accounts.AccountsDAO;
import pl.foodapp.database.*;

import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.database.AccountsEntity;
import pl.foodapp.database.BasketsEntity;
import pl.foodapp.database.DishesEntity;

import java.util.List;

public class AddBasket {
    private final BasketsDAO B_DAO;
    public AddBasket(BasketsDAO B_DAO) {
        this.B_DAO = B_DAO;
    }
    public void addBasket(BasketsDTO item) {
        checkCustomerId(item.getCustomerId());
        checkDishId(item.getDishId());

        BasketsEntity entity = new BasketsEntity();

        entity.setCustomer(item.getCustomerId());
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
