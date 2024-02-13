package pl.foodapp.accounts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.foodapp.database.AccountTypesEntity;

import java.util.List;

public class AccountTypesDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<AccountTypesEntity> getAllAccountTypes() {
        Session session = sessionFactory.openSession();
        List<AccountTypesEntity> accountTypes = session.createQuery("from AccountTypesEntity").list();
        session.close();
        return accountTypes;
    }

    public AccountTypesEntity getAccountTypeById(int id) {
        Session session = sessionFactory.openSession();
        AccountTypesEntity accountType = session.get(AccountTypesEntity.class, id);
        session.close();
        return accountType;
    }

}
