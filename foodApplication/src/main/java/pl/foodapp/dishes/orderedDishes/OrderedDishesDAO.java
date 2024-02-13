package pl.foodapp.dishes.orderedDishes;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.foodapp.database.OrderedDishesEntity;


import java.util.List;
public class OrderedDishesDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<OrderedDishesEntity> getAllDishes() {
        Session session = sessionFactory.openSession();
        List<OrderedDishesEntity> dishes = session.createQuery("from OrderedDishesEntity ").list();
        session.close();
        return dishes;
    }

    public OrderedDishesEntity getDishIdById(int id) {
        Session session = sessionFactory.openSession();
        OrderedDishesEntity dishId = session.get(OrderedDishesEntity.class, id);
        session.close();
        return dishId;
    }

    public List<OrderedDishesEntity> getDishesByOrderId(int orderId) {
        Session session = sessionFactory.openSession();
        List<OrderedDishesEntity> dishes = session.createQuery("from OrderedDishesEntity where orderId = :orderId", OrderedDishesEntity.class)
                .setParameter("orderId", orderId)
                .list();
        session.close();
        return dishes;
    }

    public List<OrderedDishesEntity> getDishesByDishId(int dishId) {
        Session session = sessionFactory.openSession();
        List<OrderedDishesEntity> dishes = session.createQuery("from OrderedDishesEntity where dishId = :dishId", OrderedDishesEntity.class)
                .setParameter("dishId", dishId)
                .list();
        session.close();
        return dishes;
    }

    public void addDish(OrderedDishesEntity dish) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(dish);
        transaction.commit();
        session.close();
    }


    public void updateDish(OrderedDishesEntity dish) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(dish);
        transaction.commit();
        session.close();
    }

    public void deleteDish(OrderedDishesEntity dish) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(dish);
        transaction.commit();
        session.close();
    }
}
