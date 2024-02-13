package pl.foodapp.statuses;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.foodapp.database.StatusesEntity;

import java.util.List;

public class StatusesDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<StatusesEntity> getAllStatuses() {
        Session session = sessionFactory.openSession();
        List<StatusesEntity> statuses = session.createQuery("from StatusesEntity").list();
        session.close();
        return statuses;
    }

    public StatusesEntity getStatusById(int id) {
        Session session = sessionFactory.openSession();
        StatusesEntity status = session.get(StatusesEntity.class, id);
        session.close();
        return status;
    }

}