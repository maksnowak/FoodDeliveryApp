package pl.foodapp.accounts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.foodapp.database.AccountsEntity;

import java.util.List;

public class AccountsDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<AccountsEntity> getAllAccounts() {
        Session session = sessionFactory.openSession();
        List<AccountsEntity> accounts = session.createQuery("from AccountsEntity").list();
        session.close();
        return accounts;
    }

    public AccountsEntity getAccountById(int id) {
        Session session = sessionFactory.openSession();
        AccountsEntity account = session.get(AccountsEntity.class, id);
        session.close();
        return account;
    }

    public AccountsEntity getAccountByEmail(String email) {
        Session session = sessionFactory.openSession();
        AccountsEntity account = session.createQuery("from AccountsEntity where email = :email", AccountsEntity.class)
                .setParameter("email", email)
                .uniqueResult();
        session.close();
        return account;
    }

    public void addAccount(AccountsEntity account) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(account);
        transaction.commit();
        session.close();
    }

    public void updateAccount(AccountsEntity account) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(account);
        transaction.commit();
        session.close();
    }

    public void deleteAccount(AccountsEntity account) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(account);
        transaction.commit();
        session.close();
    }
}
