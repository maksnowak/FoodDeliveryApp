package pap2023z.z09.complaints;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pap2023z.z09.database.ComplaintsEntity;
import pap2023z.z09.database.OrdersEntity;

import java.util.List;

public class ComplaintsDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    public List<ComplaintsEntity> getAllComplaints() {
        Session session = sessionFactory.openSession();
        List<ComplaintsEntity> complaints = session.createQuery("from ComplaintsEntity ").list();
        session.close();
        return complaints;
    }

    public List<ComplaintsEntity> getAllComplaintsConnectedToOrderId(int id) {
        Session session = sessionFactory.openSession();
        List<ComplaintsEntity> complaints = session.createQuery("from ComplaintsEntity where orderId = :id")
                .setParameter("id", id)
                .list();
        session.close();
        return complaints;
    }

    public ComplaintsEntity getComplaintsById(int id) {
        Session session = sessionFactory.openSession();
        ComplaintsEntity complaint = session.get(ComplaintsEntity.class, id);
        session.close();
        return complaint;
    }

    public void addComplaint(ComplaintsEntity complaint) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(complaint);
        transaction.commit();
        session.close();
    }


    public void updateComplaint(ComplaintsEntity complaint) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(complaint);
        transaction.commit();
        session.close();
    }

    public void deleteComplaint(ComplaintsEntity complaint) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(complaint);
        transaction.commit();
        session.close();
    }
}

