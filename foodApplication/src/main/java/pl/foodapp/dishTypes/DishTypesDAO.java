package pl.foodapp.dishTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.foodapp.database.DishTypesEntity;
import java.util.List;

public class DishTypesDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<DishTypesEntity> getAllDishTypes() {
        Session session = sessionFactory.openSession();
        List<DishTypesEntity> dishTypes = session.createQuery("from DishTypesEntity").list();
        session.close();
        return dishTypes;
    }

    public DishTypesEntity getDishTypeById(int id) {
        Session session = sessionFactory.openSession();
        DishTypesEntity dishType = session.get(DishTypesEntity.class, id);
        session.close();
        return dishType;
    }

    public DishTypesEntity getDishTypeByName(String name) {
            Session session = sessionFactory.openSession();
            DishTypesEntity dishType = session.createQuery("from DishTypesEntity where name = :name", DishTypesEntity.class)
                    .setParameter("name", name)
                    .uniqueResult();
            session.close();
            return dishType;
        }

    public void addDishType(DishTypesEntity dishType) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(dishType);
        transaction.commit();
        session.close();
    }

    public void updateDishType(DishTypesEntity dishType) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(dishType);
        transaction.commit();
        session.close();
    }

}
