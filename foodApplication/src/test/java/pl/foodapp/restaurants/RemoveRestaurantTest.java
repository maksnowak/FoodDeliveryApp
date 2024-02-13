package pl.foodapp.restaurants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.baskets.BasketsDAO;
import pl.foodapp.database.*;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDAO;
import pl.foodapp.dishes.favourites.FavoritesDAO;
import pl.foodapp.restaurants.RemoveRestaurant;
import pl.foodapp.restaurants.RestaurantsDAO;
import pl.foodapp.reviews.ReviewsDAO;
import pl.foodapp.workers.WorkersDAO;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class RemoveRestaurantTest {
    @InjectMocks
    private RemoveRestaurant removeRestaurant;

    @Mock
    private RestaurantsDAO restaurantsDAO;

    @Mock
    private DishesDAO dishesDAO;

    @Mock
    private OrderedDishesDAO orderedDishesDAO;

    @Mock
    private BasketsDAO basketsDAO;

    @Mock
    private WorkersDAO workersDAO;

    @Mock
    private FavoritesDAO favoritesDAO;

    @Mock
    private ReviewsDAO reviewsDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void removeRestaurantTest() {
        DishesEntity testDish = new DishesEntity();
        OrderedDishesEntity testOrderedDish = new OrderedDishesEntity();
        BasketsEntity testBasket = new BasketsEntity();
        FavoritesEntity testFavorite = new FavoritesEntity();
        WorkersEntity testWorker = new WorkersEntity();
        ReviewsEntity testReview = new ReviewsEntity();
        when(reviewsDAO.getAllReviewsOfResturantId(1)).thenReturn(List.of(testReview));
        when(dishesDAO.getDishesByRestaurant(1)).thenReturn(List.of(testDish));
        when(orderedDishesDAO.getDishesByDishId(1)).thenReturn(List.of(testOrderedDish));
        when(basketsDAO.getDishesByDishId(1)).thenReturn(List.of(testBasket));
        when(favoritesDAO.getFavoritesByDish(1)).thenReturn(List.of(testFavorite));
        when(workersDAO.getWorkersByRestaurantId(1)).thenReturn(List.of(testWorker));
        when(reviewsDAO.getAllReviewsOfResturantId(1)).thenReturn(List.of(testReview));
        doNothing().when(orderedDishesDAO).deleteDish(any());
        doNothing().when(basketsDAO).deleteBasket(any());
        doNothing().when(favoritesDAO).deleteFavorite(any());
        doNothing().when(dishesDAO).removeDish(anyInt());
        doNothing().when(restaurantsDAO).removeRestaurant(anyInt());
        doNothing().when(workersDAO).deleteWorker(anyInt());
        doNothing().when(reviewsDAO).deleteReview(any());
        removeRestaurant.removeRestaurant(1);
        verify(dishesDAO, times(1)).getDishesByRestaurant(anyInt());
        verify(orderedDishesDAO, times(1)).getDishesByDishId(anyInt());
        verify(basketsDAO, times(1)).getDishesByDishId(anyInt());
        verify(favoritesDAO, times(1)).getFavoritesByDish(anyInt());
        verify(workersDAO, times(1)).getWorkersByRestaurantId(anyInt());
        verify(reviewsDAO, times(1)).getAllReviewsOfResturantId(anyInt());
    }

    @Test
    public void removeRestaurantEmptyTest() {
        when(dishesDAO.getDishesByRestaurant(1)).thenReturn(Collections.emptyList());
        when(workersDAO.getWorkersByRestaurantId(1)).thenReturn(Collections.emptyList());
        when(reviewsDAO.getAllReviewsOfResturantId(1)).thenReturn(Collections.emptyList());
        removeRestaurant.removeRestaurant(1);
        verify(dishesDAO, times(1)).getDishesByRestaurant(anyInt());
        verify(orderedDishesDAO, times(0)).getDishesByDishId(anyInt());
        verify(basketsDAO, times(0)).getDishesByDishId(anyInt());
        verify(favoritesDAO, times(0)).getFavoritesByDish(anyInt());
        verify(workersDAO, times(1)).getWorkersByRestaurantId(anyInt());
        verify(reviewsDAO, times(1)).getAllReviewsOfResturantId(anyInt());
    }
}
