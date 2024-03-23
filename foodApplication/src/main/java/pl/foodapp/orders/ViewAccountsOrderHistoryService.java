package pl.foodapp.orders;

import pl.foodapp.database.OrdersEntity;

import java.util.ArrayList;
import java.util.List;

public class ViewAccountsOrderHistoryService {
    private final OrdersDAO ordersDAO;

    public ViewAccountsOrderHistoryService(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    List<OrdersDTO> getOrdersHistory(int accountId) {
        // get orders from database for account with given id
        List<OrdersEntity> orders = ordersDAO.getAllOrdersFromAccountId(accountId);
        ArrayList<OrdersDTO> dtos = new ArrayList<>();
        for (OrdersEntity order : orders) {
            dtos.add(OrdersDTO.fromEntity(order));
        }
        return dtos;
    }
}
