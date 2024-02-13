package pl.foodapp.restaurants;

import pl.foodapp.baskets.BasketsDAO;
import pl.foodapp.database.DishesEntity;
import pl.foodapp.database.ReviewsEntity;
import pl.foodapp.database.WorkersEntity;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.RemoveDish;
import pl.foodapp.dishes.favourites.FavoritesDAO;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDAO;
import pl.foodapp.reviews.ReviewsDAO;
import pl.foodapp.workers.WorkersDAO;

public class RemoveRestaurant {
    private final RestaurantsDAO restaurantsDAO;
    private final DishesDAO dishesDAO;
    private final OrderedDishesDAO orderedDishesDAO;
    private final BasketsDAO basketsDAO;
    private final FavoritesDAO favoritesDAO;
    private final WorkersDAO workersDAO;
    private final ReviewsDAO reviewsDAO;

    public RemoveRestaurant(RestaurantsDAO restaurantsDAO, DishesDAO dishesDAO, OrderedDishesDAO orderedDishesDAO, BasketsDAO basketsDAO, FavoritesDAO favoritesDAO, WorkersDAO workersDAO, ReviewsDAO reviewsDAO) {
        this.restaurantsDAO = restaurantsDAO;
        this.dishesDAO = dishesDAO;
        this.orderedDishesDAO = orderedDishesDAO;
        this.basketsDAO = basketsDAO;
        this.favoritesDAO = favoritesDAO;
        this.workersDAO = workersDAO;
        this.reviewsDAO = reviewsDAO;
    }

    public void removeRestaurant(int restaurantId) {
        RemoveDish removeDish = new RemoveDish(dishesDAO, orderedDishesDAO, basketsDAO, favoritesDAO);
        for (DishesEntity dishesEntity : dishesDAO.getDishesByRestaurant(restaurantId)) {
            removeDish.removeDish(dishesEntity.getDishId());
        }
        for (WorkersEntity workersEntity : workersDAO.getWorkersByRestaurantId(restaurantId)) {
            workersDAO.deleteWorker(workersEntity.getId());
        }
        for (ReviewsEntity reviewsEntity : reviewsDAO.getAllReviewsOfResturantId(restaurantId)) {
            reviewsDAO.deleteReview(reviewsEntity);
        }
        restaurantsDAO.removeRestaurant(restaurantId);
    }
}
