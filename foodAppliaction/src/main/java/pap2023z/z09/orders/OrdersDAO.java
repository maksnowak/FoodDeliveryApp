package pap2023z.z09.orders;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pap2023z.z09.database.OrdersEntity;

import java.util.List;

public class OrdersDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<OrdersEntity> getAllOrders() {
        Session session = sessionFactory.openSession();
        List<OrdersEntity> orders = session.createQuery("from OrdersEntity").list();
        session.close();
        return orders;
    }

    public OrdersEntity getOrderById(int id) {
        Session session = sessionFactory.openSession();
        OrdersEntity order = session.get(OrdersEntity.class, id);
        session.close();
        return order;
    }

    public List<OrdersEntity> getAllOrdersFromAccountId(int id) {
        Session session = sessionFactory.openSession();
        List<OrdersEntity> orders = session.createQuery("from OrdersEntity where customer = :id")
                .setParameter("id", id)
                .list();
        session.close();
        return orders;
    }
    public void addOrder(OrdersEntity order){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(order);
        transaction.commit();
        session.close();
    }

    public void updateOrder(OrdersEntity order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(order);
        transaction.commit();
        session.close();
    }
    public void deleteOrder(OrdersEntity order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(order);
        transaction.commit();
        session.close();
    }
}