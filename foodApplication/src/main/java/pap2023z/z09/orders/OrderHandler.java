package pap2023z.z09.orders;

import pap2023z.z09.accounts.AccountsDAO;
import pap2023z.z09.database.*;
import pap2023z.z09.discounts.DiscountsDAO;
import pap2023z.z09.paymentMethods.PaymentMethodsDAO;
import pap2023z.z09.statuses.StatusesDAO;


import java.math.BigDecimal;
import java.util.List;

public class OrderHandler {
    private final OrdersDAO orderDAO;
    public OrderHandler(OrdersDAO orderDAO){
        this.orderDAO = orderDAO;
    }
    //returns id of added order
    public int addOrder(OrdersDTO order) {
        checkStatusId(order.getStatusId());
        checkPaymentMethodId(order.getPaymentMethodId());
        checkCustomerId(order.getCustomerId());
        checkDiscountId(order.getDiscountId());

        checkIfInRange(order.getTip());
        checkIfInRange(order.getTotal());
        checkIfInRange(order.getStreetNumber());
        checkIfInRange(order.getApartment());

        OrdersEntity entity = new OrdersEntity();

        entity.setOrderId(order.getOrderId());
        entity.setStatus(order.getStatusId());
        entity.setCustomer(order.getCustomerId());
        entity.setTotal(order.getTotal());
        entity.setPaymentMethod(order.getPaymentMethodId());
        entity.setStreet(order.getStreet());
        entity.setStreetNumber(order.getStreetNumber());
        entity.setApartment(order.getApartment());
        entity.setCity(order.getCity());
        entity.setDiscount(order.getDiscountId());
        entity.setTip(order.getTip());

        orderDAO.addOrder(entity);
        return entity.getOrderId();
    }

    public int getStatusId(int orderId)
    {
        checkOrderId(orderId);
        OrdersEntity order = orderDAO.getOrderById(orderId);
        return(order.getStatus());
    }
    public void changeStatus(int orderId, int statusId)
    {
        checkStatusId(statusId);
        checkOrderId(orderId);

        OrdersEntity order = orderDAO.getOrderById(orderId);
        order.setStatus(statusId);
        orderDAO.updateOrder(order);
    }

    public void addTipAfterOrdering(int orderId, BigDecimal tip)
    {
        checkIfInRange(tip);
        checkOrderId(orderId);

        OrdersEntity order = orderDAO.getOrderById(orderId);
        order.setTip(tip);
        orderDAO.updateOrder(order);
    }

    private void checkStatusId(int id){
        StatusesDAO statusesDAO = new StatusesDAO();
        List <StatusesEntity> stats = statusesDAO.getAllStatuses();
        for(StatusesEntity stat :stats){
            if(stat.getStatusId() == id){
                return;
            }
        }
        throw new IllegalArgumentException("the foreign key StatusId does not exist in primary keys ");
    }
    private void checkCustomerId(int id){
        AccountsDAO accountsDAO = new AccountsDAO();
        List <AccountsEntity> accounts = accountsDAO.getAllAccounts();
        for(AccountsEntity account :accounts){
            if(account.getAccountId() == id){
                return;
            }
        }
        throw new IllegalArgumentException("the foreign key CustomerId does not exist in primary keys ");
    }

    private void checkPaymentMethodId(int id){
        PaymentMethodsDAO methodsDOA = new PaymentMethodsDAO();
        List <PaymentMethodsEntity> methods = methodsDOA.getAllMethods();
        for(PaymentMethodsEntity method :methods){
            if(method.getMethodId() == id){
                return;
            }
        }
        throw new IllegalArgumentException("the foreign key PaymentMethodId does not exist in primary keys ");
    }

    private void checkDiscountId(Integer id){
        if(id == null){
            return;
        }
        DiscountsDAO discountDAO = new DiscountsDAO();
        List <DiscountsEntity> discounts = discountDAO.getAllDiscounts();
        for(DiscountsEntity discount : discounts){
            if(discount.getDiscountId() == id){
                return;
            }
        }
        throw new IllegalArgumentException("the foreign key DiscountId does not exist in primary keys ");
    }
    private void checkOrderId(int id){
        List <OrdersEntity> orders = orderDAO.getAllOrders();
        for(OrdersEntity order : orders){
            if(order.getOrderId() == id){
                return;
            }
        }
        throw new IllegalArgumentException("the foreign key OrderId does not exist in primary keys ");
    }

    public void checkIfInRange(int money){
        if(money < 0){
            throw new IllegalArgumentException("one of the numbers is smaller than zero ");
        }
    }
    public void checkIfInRange(BigDecimal money){
        if(money.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("one of the numbers is smaller than zero ");
        }
    }
}
