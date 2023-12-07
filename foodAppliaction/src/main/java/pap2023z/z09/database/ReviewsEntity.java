package pap2023z.z09.database;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "reviews", schema = "public", catalog = "postgres")
public class ReviewsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "review_id", nullable = false)
    private int reviewId;
    @Basic
    @Column(name = "resturant_id", nullable = false)
    private int resturantId;
    @Basic
    @Column(name = "customer", nullable = false)
    private int customer;
    @Basic
    @Column(name = "stars", nullable = false)
    private int stars;
    @Basic
    @Column(name = "description", nullable = true, length = 500)
    private String description;
    @ManyToOne
    @JoinColumn(name = "resturant_id", referencedColumnName = "restaurant_id", nullable = false)
    private RestaurantsEntity restaurantsByResturantId;
    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "account_id", nullable = false)
    private AccountsEntity accountsByCustomer;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getResturantId() {
        return resturantId;
    }

    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewsEntity that = (ReviewsEntity) o;
        return reviewId == that.reviewId && resturantId == that.resturantId && customer == that.customer && stars == that.stars && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, resturantId, customer, stars, description);
    }

    public RestaurantsEntity getRestaurantsByResturantId() {
        return restaurantsByResturantId;
    }

    public void setRestaurantsByResturantId(RestaurantsEntity restaurantsByResturantId) {
        this.restaurantsByResturantId = restaurantsByResturantId;
    }

    public AccountsEntity getAccountsByCustomer() {
        return accountsByCustomer;
    }

    public void setAccountsByCustomer(AccountsEntity accountsByCustomer) {
        this.accountsByCustomer = accountsByCustomer;
    }
}
