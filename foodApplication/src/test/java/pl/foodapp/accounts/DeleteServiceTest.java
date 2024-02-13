package pl.foodapp.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.accounts.AccountsDAO;
import pl.foodapp.accounts.DeleteService;
import pl.foodapp.baskets.BasketsDAO;
import pl.foodapp.database.*;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.favourites.FavoritesDAO;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDAO;
import pl.foodapp.orders.OrdersDAO;
import pl.foodapp.paymentMethods.PaymentMethodsDAO;
import pl.foodapp.restaurants.RestaurantsDAO;
import pl.foodapp.reviews.ReviewsDAO;
import pl.foodapp.workers.WorkersDAO;

import java.util.List;

import static org.mockito.Mockito.*;

public class DeleteServiceTest {
    @InjectMocks
    private DeleteService deleteService;

    @Mock
    private AccountsDAO accountsDAO;

    @Mock
    private OrdersDAO ordersDAO;

    @Mock
    private PaymentMethodsDAO paymentMethodsDAO;

    @Mock
    private FavoritesDAO favoritesDAO;

    @Mock
    private BasketsDAO basketsDAO;

    @Mock
    private WorkersDAO workersDAO;

    @Mock
    private RestaurantsDAO restaurantsDAO;

    @Mock
    private DishesDAO dishesDAO;

    @Mock
    private ReviewsDAO reviewsDAO;

    @Mock
    private OrderedDishesDAO orderedDishesDAO;

    @Mock
    private AccountsEntity account;

    @Mock
    private OrdersEntity order;

    @Mock
    private PaymentMethodsEntity method;

    @Mock
    private FavoritesEntity favorite;

    @Mock
    private WorkersEntity worker;

    @Mock
    private BasketsEntity basket;

    @Mock
    private ReviewsEntity review;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteAccount() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of());
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of());
        when(favoritesDAO.getFavoritesByCustomer(1)).thenReturn(List.of());
        when(basketsDAO.getAllDishesOfClientId(1)).thenReturn(List.of());
        when(workersDAO.getWorkersByAccountId(1)).thenReturn(List.of());
        when(restaurantsDAO.getRestaurantById(1)).thenReturn(null);
        when(dishesDAO.getDishesByRestaurant(1)).thenReturn(List.of());
        when(orderedDishesDAO.getDishesByDishId(1)).thenReturn(List.of());
        when(reviewsDAO.getAllReviewsFromCustomerId(1)).thenReturn(List.of());
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteAccountWithOrders() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of(order));
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of());
        when(favoritesDAO.getFavoritesByCustomer(1)).thenReturn(List.of());
        when(basketsDAO.getAllDishesOfClientId(1)).thenReturn(List.of());
        when(workersDAO.getWorkersByAccountId(1)).thenReturn(List.of());
        when(restaurantsDAO.getRestaurantById(1)).thenReturn(null);
        when(dishesDAO.getDishesByRestaurant(1)).thenReturn(List.of());
        when(orderedDishesDAO.getDishesByDishId(1)).thenReturn(List.of());
        when(reviewsDAO.getAllReviewsFromCustomerId(1)).thenReturn(List.of());
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteAccountWithPaymentMethods() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of());
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of(method));
        when(favoritesDAO.getFavoritesByCustomer(1)).thenReturn(List.of());
        when(basketsDAO.getAllDishesOfClientId(1)).thenReturn(List.of());
        when(workersDAO.getWorkersByAccountId(1)).thenReturn(List.of());
        when(restaurantsDAO.getRestaurantById(1)).thenReturn(null);
        when(dishesDAO.getDishesByRestaurant(1)).thenReturn(List.of());
        when(orderedDishesDAO.getDishesByDishId(1)).thenReturn(List.of());
        when(reviewsDAO.getAllReviewsFromCustomerId(1)).thenReturn(List.of());
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteAccountWithFavorites() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of());
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of());
        when(favoritesDAO.getFavoritesByCustomer(1)).thenReturn(List.of(favorite));
        when(basketsDAO.getAllDishesOfClientId(1)).thenReturn(List.of());
        when(workersDAO.getWorkersByAccountId(1)).thenReturn(List.of());
        when(restaurantsDAO.getRestaurantById(1)).thenReturn(null);
        when(dishesDAO.getDishesByRestaurant(1)).thenReturn(List.of());
        when(orderedDishesDAO.getDishesByDishId(1)).thenReturn(List.of());
        when(reviewsDAO.getAllReviewsFromCustomerId(1)).thenReturn(List.of());
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteAccountWithBaskets() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of());
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of());
        when(favoritesDAO.getFavoritesByCustomer(1)).thenReturn(List.of());
        when(basketsDAO.getAllDishesOfClientId(1)).thenReturn(List.of(basket));
        when(workersDAO.getWorkersByAccountId(1)).thenReturn(List.of());
        when(restaurantsDAO.getRestaurantById(1)).thenReturn(null);
        when(dishesDAO.getDishesByRestaurant(1)).thenReturn(List.of());
        when(orderedDishesDAO.getDishesByDishId(1)).thenReturn(List.of());
        when(reviewsDAO.getAllReviewsFromCustomerId(1)).thenReturn(List.of());
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteAccountWithReviews() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of());
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of());
        when(favoritesDAO.getFavoritesByCustomer(1)).thenReturn(List.of());
        when(basketsDAO.getAllDishesOfClientId(1)).thenReturn(List.of());
        when(workersDAO.getWorkersByAccountId(1)).thenReturn(List.of());
        when(restaurantsDAO.getRestaurantById(1)).thenReturn(null);
        when(dishesDAO.getDishesByRestaurant(1)).thenReturn(List.of());
        when(orderedDishesDAO.getDishesByDishId(1)).thenReturn(List.of());
        when(reviewsDAO.getAllReviewsFromCustomerId(1)).thenReturn(List.of(review));
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteAccountWithWorkers() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of());
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of());
        when(favoritesDAO.getFavoritesByCustomer(1)).thenReturn(List.of());
        when(basketsDAO.getAllDishesOfClientId(1)).thenReturn(List.of());
        when(workersDAO.getWorkersByAccountId(1)).thenReturn(List.of(worker));
        when(restaurantsDAO.getRestaurantById(1)).thenReturn(null);
        when(dishesDAO.getDishesByRestaurant(1)).thenReturn(List.of());
        when(orderedDishesDAO.getDishesByDishId(1)).thenReturn(List.of());
        when(reviewsDAO.getAllReviewsFromCustomerId(1)).thenReturn(List.of());
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteAccountWithOrdersAndPaymentMethods() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of(order));
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of(method));
        when(favoritesDAO.getFavoritesByCustomer(1)).thenReturn(List.of());
        when(basketsDAO.getAllDishesOfClientId(1)).thenReturn(List.of());
        when(workersDAO.getWorkersByAccountId(1)).thenReturn(List.of());
        when(restaurantsDAO.getRestaurantById(1)).thenReturn(null);
        when(dishesDAO.getDishesByRestaurant(1)).thenReturn(List.of());
        when(orderedDishesDAO.getDishesByDishId(1)).thenReturn(List.of());
        when(reviewsDAO.getAllReviewsFromCustomerId(1)).thenReturn(List.of());
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteAccountWithEverything() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of(order));
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of(method));
        when(favoritesDAO.getFavoritesByCustomer(1)).thenReturn(List.of(favorite));
        when(basketsDAO.getAllDishesOfClientId(1)).thenReturn(List.of(basket));
        when(workersDAO.getWorkersByAccountId(1)).thenReturn(List.of(worker));
        when(restaurantsDAO.getRestaurantById(1)).thenReturn(null);
        when(dishesDAO.getDishesByRestaurant(1)).thenReturn(List.of());
        when(orderedDishesDAO.getDishesByDishId(1)).thenReturn(List.of());
        when(reviewsDAO.getAllReviewsFromCustomerId(1)).thenReturn(List.of(review));
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteNoAccount() {
        when(accountsDAO.getAccountById(1)).thenReturn(null);
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(0)).deleteAccount(account);
    }
}
