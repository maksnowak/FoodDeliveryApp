package pap2023z.z09.paymentMethods;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pap2023z.z09.database.PaymentMethodsEntity;


import java.util.List;
public class PaymentMethodsDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<PaymentMethodsEntity> getAllMethods() {
        Session session = sessionFactory.openSession();
        List<PaymentMethodsEntity> methods = session.createQuery("from PaymentMethodsEntity ").list();
        session.close();
        return methods;
    }

    public PaymentMethodsEntity getMethodIdById(int id) {
        Session session = sessionFactory.openSession();
        PaymentMethodsEntity method = session.get(PaymentMethodsEntity.class, id);
        session.close();
        return method;
    }

    public List<PaymentMethodsEntity> getMethodsByCustomerId(int id) {
        Session session = sessionFactory.openSession();
        List<PaymentMethodsEntity> methods = session.createQuery("from PaymentMethodsEntity where customer = " + id).list();
        session.close();
        return methods;
    }

    public void addMethod(PaymentMethodsEntity method) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(method);
        transaction.commit();
        session.close();
    }


    public void updateMethod(PaymentMethodsEntity method) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(method);
        transaction.commit();
        session.close();
    }

    public void deleteMethod(PaymentMethodsEntity method) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(method);
        transaction.commit();
        session.close();
    }
}