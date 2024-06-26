package pl.foodapp.reviews;


import pl.foodapp.accounts.AccountsDAO;
import pl.foodapp.database.AccountsEntity;
import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.database.ReviewsEntity;
import pl.foodapp.restaurants.RestaurantsDAO;

import java.util.List;

public class AddReviews {
    private final ReviewsDAO reviewsDAO;

    public AddReviews(ReviewsDAO reviewsDAO)
    {
        this.reviewsDAO = reviewsDAO;
    }
    public void addReview(ReviewsDTO review)
    {
        checkCustomerId(review.getCustomer());
        checkRestaurantId(review.getRestaurantId());
        checkIfStarsInRange(review.getStars());

        ReviewsEntity entity = new ReviewsEntity();

        entity.setCustomer(review.getCustomer());
        entity.setRestaurantId(review.getRestaurantId());
        entity.setStars(review.getStars());
        entity.setDescription(review.getDescription());

        reviewsDAO.addReview(entity);
    }

    private void checkRestaurantId(int id){
        RestaurantsDAO restaurantsDAO = new RestaurantsDAO();

        List<RestaurantsEntity> restaurants = restaurantsDAO.getAllRestaurants();
        for(RestaurantsEntity restaurant : restaurants){
            if(restaurant.getRestaurantId() == id){
                return;
            }
        }
        throw new IllegalArgumentException("the foreign key DiscountId does not exist in primary keys ");
    }
    private void checkCustomerId(int id){
        AccountsDAO accountsDAO = new AccountsDAO();

        List<AccountsEntity> accounts = accountsDAO.getAllAccounts();
        for(AccountsEntity account : accounts){
            if(account.getAccountId() == id){
                return;
            }
        }
        throw new IllegalArgumentException("the foreign key DiscountId does not exist in primary keys ");
    }

    public void checkIfStarsInRange(int stars){
        if(stars < 0 || stars > 5){
            throw new IllegalArgumentException("one of the numbers is smaller than zero or too big for a star counter");
        }
    }
}
