package pl.foodapp.discounts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.foodapp.database.DiscountsEntity;
import java.util.List;

public class DiscountsDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<DiscountsEntity> getAllDiscounts() {
        Session session = sessionFactory.openSession();
        List<DiscountsEntity> discounts = session.createQuery("from DiscountsEntity").list();
        session.close();
        return discounts;
    }

    public DiscountsEntity getDiscountById(int id) {
        Session session = sessionFactory.openSession();
        DiscountsEntity discount = session.get(DiscountsEntity.class, id);
        session.close();
        return discount;
    }

    public DiscountsEntity getDiscountByCode(String code) {
        Session session = sessionFactory.openSession();
        DiscountsEntity discount = session.createQuery("from DiscountsEntity where code = :code", DiscountsEntity.class)
                .setParameter("code", code)
                .uniqueResult();
        session.close();
        return discount;
    }

    public void addDiscount(DiscountsEntity discount) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(discount);
        transaction.commit();
        session.close();
    }


}
