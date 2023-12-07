package pap2023z.z09;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pap2023z.z09.database.RestaurantsEntity;

import java.util.List;

// Testowanie połączenia z bazą
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<RestaurantsEntity> restaurants = session.createQuery("from RestaurantsEntity").list();
        for (RestaurantsEntity restaurant : restaurants) {
            System.out.println(restaurant.getName());
        }
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}