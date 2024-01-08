package pap2023z.z09.orders;

import pap2023z.z09.accounts.AccountsDAO;
import pap2023z.z09.database.OrdersEntity;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;

import java.util.ArrayList;
import java.util.List;

public class ViewAccountsOrderHistory {
    private final AccountsDAO accountsDAO;
    private final OrdersDAO ordersDAO;
    private final OrderedDishesDAO orderedDishesDAO;

    public ViewAccountsOrderHistory(AccountsDAO accountsDAO, OrdersDAO ordersDAO, OrderedDishesDAO orderedDishesDAO) {
        this.accountsDAO = accountsDAO;
        this.ordersDAO = ordersDAO;
        this.orderedDishesDAO = orderedDishesDAO;
    }

    List<OrdersDTO> getOrdersHistory(int accountId) {
        List<OrdersEntity> orders = ordersDAO.getAllOrdersFromAccountId(accountId);
        ArrayList<OrdersDTO> dtos = new ArrayList<>();
        for (OrdersEntity order : orders) {
            dtos.add(OrdersDTO.fromEntity(order));
        }
        return dtos;
    }
}
