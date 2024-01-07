package pap2023z.z09;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pap2023z.z09.baskets.AddBasket;
import pap2023z.z09.baskets.BasketsDAO;
import pap2023z.z09.baskets.BasketsDTO;
import pap2023z.z09.database.BasketsEntity;
import pap2023z.z09.database.OrderedDishesEntity;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.orderedDishes.AddOrderedDishes;
import pap2023z.z09.orderedDishes.OrderedDishesDAO;
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


        DishesDAO D_DAO = new DishesDAO();
        BasketsDAO B_DAO = new BasketsDAO();

        AddBasket basket = new AddBasket(B_DAO);

        int CLIENTID = 2;

        BasketsDTO item = new BasketsDTO();

        item.setDishId(D_DAO.getAllDishes().get(1).getDishId());
        item.setCustomerId(CLIENTID);

        basket.addBasket(item);

        item.setDishId(D_DAO.getAllDishes().get(2).getDishId());
        basket.addBasket(item);

        item.setDishId(D_DAO.getAllDishes().get(3).getDishId());
        basket.addBasket(item);

/////////////////////////////////
        System.out.println("\ninside basket Database during\n");

        List<BasketsEntity> dishes = session.createQuery("from BasketsEntity ").list();
        for (BasketsEntity dish : dishes) {
            System.out.println("ID:" + dish.getId() + "  DishID:" + dish.getDishId() + "  customerID:" + dish.getCustomer());
        }
////////////////////////////////////
        OrderedDishesDAO OD_DAO = new OrderedDishesDAO();
        AddOrderedDishes orderedDishes = new AddOrderedDishes(OD_DAO, B_DAO, CLIENTID);

        //create order from gathered data -begin

        OrdersDAO O_DAO = new OrdersDAO();
        OrdersDTO order = new OrdersDTO();
        AddOrder addOrder = new AddOrder(O_DAO);

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
        //create order from gathered data -end

        orderedDishes.addOrderedDishes(orderID);


        System.out.println("\ninside basket Database after\n");
        dishes = session.createQuery("from BasketsEntity ").list();

        for (BasketsEntity dish : dishes) {
            System.out.println("ID:" + dish.getId() + "  DishID:" + dish.getDishId() + "  customerID:" + dish.getCustomer());
        }

        System.out.println("\ninside ordereddishes Database\n");
        List<OrderedDishesEntity> ODs = session.createQuery("from OrderedDishesEntity ").list();

        for (OrderedDishesEntity OD : ODs) {
            System.out.println("ID:" + OD.getId() + "  DishID:" + OD.getDishId() + "  orderID:" + OD.getOrderId());
        }

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}