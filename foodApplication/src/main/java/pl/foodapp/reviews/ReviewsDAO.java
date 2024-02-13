package pl.foodapp.reviews;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.foodapp.database.ReviewsEntity;

import java.util.List;

public class ReviewsDAO {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    public List<ReviewsEntity> getAllReviews() {
        Session session = sessionFactory.openSession();
        List<ReviewsEntity> reviews = session.createQuery("from ReviewsEntity ").list();
        session.close();
        return reviews;

    }

    public List<ReviewsEntity> getAllReviewsFromCustomerId(int id) {
        Session session = sessionFactory.openSession();
        List<ReviewsEntity> reviews = session.createQuery("from ReviewsEntity where customer = :id")
                .setParameter("id", id)
                .list();
        session.close();
        return reviews;
    }

    public List<ReviewsEntity> getAllReviewsOfResturantId(int id) {
        Session session = sessionFactory.openSession();
        List<ReviewsEntity> reviews = session.createQuery("from ReviewsEntity where reviewId = :id")
                .setParameter("id", id)
                .list();
        session.close();
        return reviews;
    }

    public ReviewsEntity getReviewById(int id) {
        Session session = sessionFactory.openSession();
        ReviewsEntity review = session.get(ReviewsEntity.class, id);
        session.close();
        return review;
    }

    public void addReview(ReviewsEntity review) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(review);
        transaction.commit();
        session.close();
    }


    public void updateReview(ReviewsEntity review) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(review);
        transaction.commit();
        session.close();
    }

    public void deleteReview(ReviewsEntity review) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(review);
        transaction.commit();
        session.close();
    }
}