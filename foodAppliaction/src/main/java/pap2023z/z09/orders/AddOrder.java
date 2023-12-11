package pap2023z.z09.orders;

import pap2023z.z09.accounts.AccountsDAO;
import pap2023z.z09.database.*;
import pap2023z.z09.discounts.DiscountsDAO;
import pap2023z.z09.paymentMethods.PaymentMethodsDOA;


import java.math.BigDecimal;
import java.util.List;

public class AddOrder {
    private final OrdersDAO orderDAO = new OrdersDAO();
    public AddOrder(){

    }

    public void addOrder(OrdersDTO order) {


        //checkStatusId(order.getStatusId());
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

    public void checkPaymentMethodId(int id){
        PaymentMethodsDOA methodsDOA = new PaymentMethodsDOA();
        List <PaymentMethodsEntity> methods = methodsDOA.getAllMethods();
        for(PaymentMethodsEntity method :methods){
            if(method.getMethodId() == id){
                return;
            }
        }
        throw new IllegalArgumentException("the foreign key MethodId does not exist in primary keys ");
    }

    public void checkDiscountId(int id){
        DiscountsDAO discountDAO = new DiscountsDAO();
        List <DiscountsEntity> discounts = discountDAO.getAllDiscounts();
        for(DiscountsEntity discount : discounts){
            if(discount.getDiscountId() == id){
                return;
            }
        }
        throw new IllegalArgumentException("the foreign key discountId does not exist in primary keys ");
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
