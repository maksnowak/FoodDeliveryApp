package pap2023z.z09;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.database.OrderedDishesEntity;
import pap2023z.z09.database.OrdersEntity;
import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.gui.App;
import pap2023z.z09.orderedDishes.OrderedDishesDAO;
import pap2023z.z09.orderedDishes.Basket;

import pap2023z.z09.orders.AddOrder;
import pap2023z.z09.orders.OrdersDAO;
import pap2023z.z09.orders.OrdersDTO;

import java.math.BigDecimal;
import java.util.List;

// Testowanie połączenia z bazą
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

//////////////////create order

        OrdersDAO OD = new OrdersDAO();
        OrdersDTO order = new OrdersDTO();
        AddOrder addOrder = new AddOrder(OD);

        order.setCustomerId(1);
        order.setTotal(new BigDecimal(0));
        order.setPaymentMethodId(1);
        order.setStreet("ulica");
        order.setStreetNumber(1);
        order.setApartment(1);
        order.setCity("miasto");
        order.setDiscountId(1);
        order.setStatusId(1);
        order.setTip(new BigDecimal(0));

        int orderID = (addOrder.addOrder(order));

        /////////////////crete basket

        OrderedDishesDAO ODD = new OrderedDishesDAO();
        Basket basket = new Basket(orderID, ODD);

        basket.addItem(ODD.getAllDishes().get(1).getId());
        basket.addItem(ODD.getAllDishes().get(2).getId());
        basket.addItem(ODD.getAllDishes().get(3).getId());

        System.out.println(basket.getAllDishes());
        basket.removeByIndex(1);

        System.out.println(basket.getAllDishes());
        basket.orderReady();

        System.out.println("\ninside orderedDishes Database\n");

        List<OrderedDishesEntity> dishes = session.createQuery("from OrderedDishesEntity ").list();
        for (OrderedDishesEntity dish : dishes) {
            System.out.println(dish.getId() + " " + dish.getDishId() + " " + dish.getOrderId());
        }

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}