package pap2023z.z09.workers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pap2023z.z09.database.WorkersEntity;

import java.util.List;

public class WorkersDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<WorkersEntity> getAllWorkers() {
        Session session = sessionFactory.openSession();
        List<WorkersEntity> workers = session.createQuery("from WorkersEntity").list();
        session.close();
        return workers;
    }

    public WorkersEntity getWorkerById(int id) {
        Session session = sessionFactory.openSession();
        WorkersEntity worker = session.get(WorkersEntity.class, id);
        session.close();
        return worker;
    }

    public WorkersEntity getWorkerByAccountId(int accountId) {
        Session session = sessionFactory.openSession();
        WorkersEntity worker = session.createQuery("from WorkersEntity where worker = :accountId", WorkersEntity.class)
                .setParameter("accountId", accountId)
                .getSingleResult();
        session.close();
        return worker;
    }

    public void addWorker(int accountId, int restaurantId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        WorkersEntity worker = new WorkersEntity();
        worker.setWorker(accountId);
        worker.setRestaurantId(restaurantId);
        session.save(worker);
        session.getTransaction().commit();
        session.close();
    }

    public void removeWorker(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        WorkersEntity worker = session.get(WorkersEntity.class, id);
        session.delete(worker);
        session.getTransaction().commit();
        session.close();
    }

    public void updateWorker(int id, int accountId, int restaurantId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        WorkersEntity worker = session.get(WorkersEntity.class, id);
        worker.setWorker(accountId);
        worker.setRestaurantId(restaurantId);
        session.update(worker);
        session.getTransaction().commit();
        session.close();
    }

    public List<WorkersEntity> getWorkersByRestaurantId(int restaurantId) {
        Session session = sessionFactory.openSession();
        List<WorkersEntity> workers = session.createQuery("from WorkersEntity where restaurantId = :restaurantId", WorkersEntity.class)
                .setParameter("restaurantId", restaurantId)
                .list();
        session.close();
        return workers;
    }

    public List<WorkersEntity> getWorkersByAccountId(int accountId) {
        Session session = sessionFactory.openSession();
        List<WorkersEntity> workers = session.createQuery("from WorkersEntity where worker = :accountId", WorkersEntity.class)
                .setParameter("accountId", accountId)
                .list();
        session.close();
        return workers;
    }
}
