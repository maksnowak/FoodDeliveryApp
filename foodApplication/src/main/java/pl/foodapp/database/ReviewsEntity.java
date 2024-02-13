package pl.foodapp.database;

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
    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;
    @Basic
    @Column(name = "customer", nullable = true)
    private Integer customer;
    @Basic
    @Column(name = "stars", nullable = false)
    private int stars;
    @Basic
    @Column(name = "description", nullable = true, length = 500)
    private String description;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id", nullable = false, insertable = false, updatable = false)
    private RestaurantsEntity restaurantsByRestaurantId;
    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "account_id", insertable = false, updatable = false)
    private AccountsEntity accountsByCustomer;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
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
        return reviewId == that.reviewId && restaurantId == that.restaurantId && stars == that.stars && Objects.equals(customer, that.customer) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, restaurantId, customer, stars, description);
    }

    public RestaurantsEntity getRestaurantsByRestaurantId() {
        return restaurantsByRestaurantId;
    }

    public void setRestaurantsByRestaurantId(RestaurantsEntity restaurantsByRestaurantId) {
        this.restaurantsByRestaurantId = restaurantsByRestaurantId;
    }

    public AccountsEntity getAccountsByCustomer() {
        return accountsByCustomer;
    }

    public void setAccountsByCustomer(AccountsEntity accountsByCustomer) {
        this.accountsByCustomer = accountsByCustomer;
    }
}
