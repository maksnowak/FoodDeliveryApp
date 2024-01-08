package pap2023z.z09.restaurants;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pap2023z.z09.database.RestaurantsEntity;
import java.util.List;

public class RestaurantsDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<RestaurantsEntity> getAllRestaurants() {
        Session session = sessionFactory.openSession();
        List<RestaurantsEntity> restaurants = session.createQuery("from RestaurantsEntity").list();
        session.close();
        return restaurants;
    }

    public RestaurantsEntity getRestaurantById(int id) {
        Session session = sessionFactory.openSession();
        RestaurantsEntity restaurant = session.get(RestaurantsEntity.class, id);
        session.close();
        return restaurant;
    }

    public RestaurantsEntity getRestaurantByName(String name) {
        Session session = sessionFactory.openSession();
        RestaurantsEntity restaurant = session.createQuery("from RestaurantsEntity where name = :name", RestaurantsEntity.class)
                .setParameter("name", name)
                .uniqueResult();
        session.close();
        return restaurant;
    }

    public void addRestaurant(RestaurantsEntity restaurant) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(restaurant);
        transaction.commit();
        session.close();
    }

    public void updateRestaurant(RestaurantsEntity restaurant) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(restaurant);
        transaction.commit();
        session.close();
    }


}
