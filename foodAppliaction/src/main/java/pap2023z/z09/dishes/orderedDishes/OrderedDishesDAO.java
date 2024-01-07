package pap2023z.z09.dishes.orderedDishes;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pap2023z.z09.database.OrderedDishesEntity;


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
