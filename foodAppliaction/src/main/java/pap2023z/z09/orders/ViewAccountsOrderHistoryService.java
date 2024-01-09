package pap2023z.z09.orders;

import pap2023z.z09.accounts.AccountsDAO;
import pap2023z.z09.database.OrdersEntity;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;

import java.util.ArrayList;
import java.util.List;

public class ViewAccountsOrderHistoryService {
    private final OrdersDAO ordersDAO;

    public ViewAccountsOrderHistoryService(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    List<OrdersDTO> getOrdersHistory(int accountId) {
        // pobierz z bazdy danych zamówienia dla konta o podanym id
        List<OrdersEntity> orders = ordersDAO.getAllOrdersFromAccountId(accountId);
        ArrayList<OrdersDTO> dtos = new ArrayList<>();
        for (OrdersEntity order : orders) {
            dtos.add(OrdersDTO.fromEntity(order));
        }
        return dtos;
    }
}
