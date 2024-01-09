package pap2023z.z09.dishes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pap2023z.z09.database.DishesEntity;
import java.util.List;

public class DishesDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<DishesEntity> getAllDishes() {
        Session session = sessionFactory.openSession();
        List<DishesEntity> dishes = session.createQuery("from DishesEntity").list();
        session.close();
        return dishes;
    }

    public List<DishesEntity> getDishesByRestaurant(int restaurantId) {
        Session session = sessionFactory.openSession();
        List<DishesEntity> dishes = session.createQuery("from DishesEntity where restaurantId = :restaurantId", DishesEntity.class)
                .setParameter("restaurantId", restaurantId)
                .list();
        session.close();
        return dishes;
    }

    public DishesEntity getDishById(int id) {
        Session session = sessionFactory.openSession();
        DishesEntity dish = session.get(DishesEntity.class, id);
        session.close();
        return dish;
    }

    public DishesEntity getDishByName(String name) {
            Session session = sessionFactory.openSession();
            DishesEntity dish = session.createQuery("from DishesEntity where name = :name", DishesEntity.class)
                    .setParameter("name", name)
                    .uniqueResult();
            session.close();
            return dish;
        }

    public int addDish(DishesEntity dish) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(dish);
        transaction.commit();
        session.close();
        return dish.getDishId();
    }

    public void updateDish(DishesEntity dish) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(dish);
        transaction.commit();
        session.close();
    }

    public void removeDish(int dishId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        DishesEntity dish = session.get(DishesEntity.class, dishId);
        if (dish != null) {
            session.remove(dish);
        }
        transaction.commit();
        session.close();
    }
}
