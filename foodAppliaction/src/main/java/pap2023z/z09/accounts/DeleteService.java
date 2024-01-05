package pap2023z.z09.accounts;

import pap2023z.z09.database.AccountsEntity;
import pap2023z.z09.database.OrdersEntity;
import pap2023z.z09.orders.OrdersDAO;


import java.util.List;

public class DeleteService {
    private final AccountsDAO accountsDAO;
    private final OrdersDAO ordersDAO;

    public DeleteService(AccountsDAO accountsDAO, OrdersDAO ordersDAO) {
        this.accountsDAO = accountsDAO;
        this.ordersDAO = ordersDAO;
    }

    public void deleteAccount(int id) {
        AccountsEntity account = accountsDAO.getAccountById(id);
        if (account != null) {
            List<OrdersEntity> orders = ordersDAO.getAllOrdersFromAccountId(id);
            for (OrdersEntity order : orders) {
                order.setCustomer(null);
                ordersDAO.updateOrder(order);
            }
            accountsDAO.deleteAccount(account);
        }
    }
}
