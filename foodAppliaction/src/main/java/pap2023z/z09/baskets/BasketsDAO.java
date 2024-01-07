package pap2023z.z09.baskets;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pap2023z.z09.database.BasketsEntity;

import java.util.List;
public class BasketsDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<BasketsEntity> getAllDishes() {
        Session session = sessionFactory.openSession();
        List<BasketsEntity> dishes = session.createQuery("from OrderedDishesEntity ").list();
        session.close();
        return dishes;
    }
    public List<BasketsEntity> getAllDishesOfClientId(int id) {
        Session session = sessionFactory.openSession();
        List<BasketsEntity> dishes = session.createQuery("from BasketsEntity where customer = :id")
                .setParameter("id", id)
                .list();
        session.close();
        return dishes;
    }

    public BasketsEntity getDishByBasketId(int id) {
        Session session = sessionFactory.openSession();
        BasketsEntity dish = session.get(BasketsEntity.class, id);
        session.close();
        return dish;
    }

    public void addBasket(BasketsEntity dish) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(dish);
        transaction.commit();
        session.close();
    }


    public void updateBasket(BasketsEntity dish) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(dish);
        transaction.commit();
        session.close();
    }

    public void deleteBasket(BasketsEntity dish) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(dish);
        transaction.commit();
        session.close();
    }
}
