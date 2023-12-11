package pap2023z.z09.accounts;

import pap2023z.z09.database.AccountsEntity;
import pap2023z.z09.database.OrdersEntity;
import pap2023z.z09.orders.OrdersDAO;


import java.util.List;

public class DeleteService {
    private final AccountsDAO accountsDAO;

    public DeleteService(AccountsDAO accountsDAO) {
        this.accountsDAO = accountsDAO;
    }

    public void deleteAccount(int id) {
        AccountsEntity account = accountsDAO.getAccountById(id);
        if (account != null) {
            List<OrdersEntity> orders = OrdersDAO.getAllOrdersFromAccountId(id);
            for (OrdersEntity order : orders) {
                order.setCustomer(null);
                OrdersDAO.updateOrder(order);
            }
            accountsDAO.deleteAccount(account);
        }
    }
}
