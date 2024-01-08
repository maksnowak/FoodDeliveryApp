package pap2023z.z09.reviews;

import pap2023z.z09.database.ReviewsEntity;

public class ReviewsDTO {
    private int reviewId;
    private int restaurantId;
    private int customer; //id
    private int stars;
    private String description;

    public ReviewsDTO(int reviewId, int restaurantId, int customer, int stars, String description){
        this.reviewId = reviewId;
        this.restaurantId = restaurantId;
        this.customer = customer;
        this.stars = stars;
        this.description = description;
    }

    public ReviewsDTO(){

    }

    //getters
    public int getReviewId() {
        return reviewId;
    }

    public int getCustomer() {
        return customer;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getStars() {
        return stars;
    }

    public String getDescription() {
        return description;
    }

    //setters


    public void setDescription(String description) {
        this.description = description;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setRestaurantId(int resturantId) {
        this.restaurantId = resturantId;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public static ReviewsDTO fromEntity(ReviewsEntity entity) {
        return new ReviewsDTO(
                entity.getReviewId(),
                entity.getRestaurantId(),
                entity.getCustomer(),
                entity.getStars(),
                entity.getDescription()
        );
    }
}