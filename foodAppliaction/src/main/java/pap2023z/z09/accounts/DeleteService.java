package pap2023z.z09.accounts;

import pap2023z.z09.database.AccountsEntity;
import pap2023z.z09.database.OrdersEntity;
import pap2023z.z09.database.PaymentMethodsEntity;
import pap2023z.z09.orders.OrdersDAO;
import pap2023z.z09.paymentMethods.DeleteCreditCardService;
import pap2023z.z09.paymentMethods.PaymentMethodsDAO;


import java.util.List;

public class DeleteService {
    private final AccountsDAO accountsDAO;
    private final OrdersDAO ordersDAO;
    private final PaymentMethodsDAO paymentMethodsDAO;

    public DeleteService(AccountsDAO accountsDAO, OrdersDAO ordersDAO, PaymentMethodsDAO paymentMethodsDAO) {
        this.accountsDAO = accountsDAO;
        this.ordersDAO = ordersDAO;
        this.paymentMethodsDAO = paymentMethodsDAO;
    }

    public void deleteAccount(int id) {
        AccountsEntity account = accountsDAO.getAccountById(id);
        if (account != null) {
            List<OrdersEntity> orders = ordersDAO.getAllOrdersFromAccountId(id);
            for (OrdersEntity order : orders) {
                order.setCustomer(null);
                ordersDAO.updateOrder(order);
            }
            List<PaymentMethodsEntity> methods = paymentMethodsDAO.getMethodsByCustomerId(id);
            DeleteCreditCardService deleteCreditCardService = new DeleteCreditCardService(paymentMethodsDAO);
            for (PaymentMethodsEntity method : methods) {
                deleteCreditCardService.deleteCreditCard(method.getMethodId());
            }
            accountsDAO.deleteAccount(account);
        }
    }
}
