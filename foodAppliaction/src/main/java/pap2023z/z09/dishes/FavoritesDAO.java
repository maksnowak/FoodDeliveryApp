package pap2023z.z09.dishes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pap2023z.z09.database.FavoritesEntity;
import java.util.List;

public class FavoritesDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<FavoritesEntity> getAllFavorites() {
        Session session = sessionFactory.openSession();
        List<FavoritesEntity> favorites = session.createQuery("from FavoritesEntity").list();
        session.close();
        return favorites;
    }

    public List<FavoritesEntity> getFavoritesByCustomer(int customerId) {
        Session session = sessionFactory.openSession();
        List<FavoritesEntity> favorites = session.createQuery("from FavoritesEntity where customer = :customerId", FavoritesEntity.class)
                .setParameter("customerId", customerId)
                .list();
        session.close();
        return favorites;
    }

    public List<FavoritesEntity> getFavoritesByDish(int dishId) {
        Session session = sessionFactory.openSession();
        List<FavoritesEntity> favorites = session.createQuery("from FavoritesEntity where dishId = :dishId", FavoritesEntity.class)
                .setParameter("dishId", dishId)
                .list();
        session.close();
        return favorites;
    }

    public FavoritesEntity getFavoriteById(int id) {
        Session session = sessionFactory.openSession();
        FavoritesEntity favorite = session.get(FavoritesEntity.class, id);
        session.close();
        return favorite;
    }

    public void addFavorite(FavoritesEntity favorite) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(favorite);
        transaction.commit();
        session.close();
    }

    public void deleteFavorite(FavoritesEntity favorite) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(favorite);
        transaction.commit();
        session.close();
    }
}
