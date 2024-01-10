package pap2023z.z09.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.baskets.BasketsDAO;
import pap2023z.z09.database.*;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.dishes.favourites.FavoritesDAO;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;
import pap2023z.z09.orders.OrdersDAO;
import pap2023z.z09.paymentMethods.PaymentMethodsDAO;
import pap2023z.z09.restaurants.RestaurantsDAO;
import pap2023z.z09.workers.WorkersDAO;

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
