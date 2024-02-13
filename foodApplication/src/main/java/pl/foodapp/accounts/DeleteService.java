package pl.foodapp.accounts;

import pl.foodapp.baskets.BasketsDAO;
import pl.foodapp.database.*;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.favourites.DeleteFavoriteService;
import pl.foodapp.dishes.favourites.FavoritesDAO;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDAO;
import pl.foodapp.orders.OrdersDAO;
import pl.foodapp.paymentMethods.DeleteCreditCardService;
import pl.foodapp.paymentMethods.PaymentMethodsDAO;
import pl.foodapp.restaurants.RemoveRestaurant;
import pl.foodapp.restaurants.RestaurantsDAO;
import pl.foodapp.reviews.ReviewsDAO;
import pl.foodapp.workers.WorkersDAO;
import pl.foodapp.database.*;


import java.util.List;

public class DeleteService {
    private final AccountsDAO accountsDAO;
    private final OrdersDAO ordersDAO;
    private final PaymentMethodsDAO paymentMethodsDAO;
    private final FavoritesDAO favoritesDAO;
    private final BasketsDAO basketsDAO;
    private final WorkersDAO workersDAO;
    private final RestaurantsDAO restaurantsDAO;
    private final DishesDAO dishesDAO;
    private final OrderedDishesDAO orderedDishesDAO;
    private final ReviewsDAO reviewsDAO;

    public DeleteService(AccountsDAO accountsDAO, OrdersDAO ordersDAO, PaymentMethodsDAO paymentMethodsDAO, FavoritesDAO favoritesDAO, BasketsDAO basketsDAO, WorkersDAO workersDAO, RestaurantsDAO restaurantsDAO, DishesDAO dishesDAO, OrderedDishesDAO orderedDishesDAO, ReviewsDAO reviewsDAO) {
        this.accountsDAO = accountsDAO;
        this.ordersDAO = ordersDAO;
        this.paymentMethodsDAO = paymentMethodsDAO;
        this.favoritesDAO = favoritesDAO;
        this.basketsDAO = basketsDAO;
        this.workersDAO = workersDAO;
        this.restaurantsDAO = restaurantsDAO;
        this.dishesDAO = dishesDAO;
        this.orderedDishesDAO = orderedDishesDAO;
        this.reviewsDAO = reviewsDAO;
    }

    public void deleteAccount(int id) {
        AccountsEntity account = accountsDAO.getAccountById(id);
        if (account != null) {
            // we wszystkich zamówieniach złożonych przez klienta ustaw pole customer na null
            List<OrdersEntity> orders = ordersDAO.getAllOrdersFromAccountId(id);
            for (OrdersEntity order : orders) {
                order.setCustomer(null);
                ordersDAO.updateOrder(order);
            }
            // usuń wszystkie metody płatności klienta
            List<PaymentMethodsEntity> methods = paymentMethodsDAO.getMethodsByCustomerId(id);
            DeleteCreditCardService deleteCreditCardService = new DeleteCreditCardService(paymentMethodsDAO);
            for (PaymentMethodsEntity method : methods) {
                deleteCreditCardService.deleteCreditCard(method.getMethodId());
            }
            // usuń wszystkie ulubione dania klienta
            List<FavoritesEntity> favorites = favoritesDAO.getFavoritesByCustomer(id);
            DeleteFavoriteService deleteFavoriteService = new DeleteFavoriteService(favoritesDAO);
            for (FavoritesEntity favorite : favorites) {
                deleteFavoriteService.deleteFavorite(favorite.getId());
            }
            // usuń wszystkie dania z koszyka klienta
            List<BasketsEntity> baskets = basketsDAO.getAllDishesOfClientId(id);
            for (BasketsEntity basket : baskets) {
                basketsDAO.deleteBasket(basket);
            }
            // usuń wszystkie opinie klienta
            List<ReviewsEntity> reviews = reviewsDAO.getAllReviewsFromCustomerId(id);
            for (ReviewsEntity review : reviews) {
                reviewsDAO.deleteReview(review);
            }
            // jeśli to jest konto pracownika, usuń go z restauracji
            List<WorkersEntity> workers = workersDAO.getWorkersByAccountId(id);
            for (WorkersEntity worker : workers) {
                // sprawdź czy w restauracji pracuje tylko jeden pracownik
                List<WorkersEntity> workersInRestaurant = workersDAO.getWorkersByRestaurantId(worker.getRestaurantId());
                if (workersInRestaurant.size() == 1) {
                    // jeśli tak, usuń restaurację
                    RemoveRestaurant removeRestaurant = new RemoveRestaurant(restaurantsDAO, dishesDAO, orderedDishesDAO, basketsDAO, favoritesDAO, workersDAO, reviewsDAO);
                    removeRestaurant.removeRestaurant(worker.getRestaurantId());
                } else {
                    // jeśli nie, usuń pracownika
                    workersDAO.deleteWorker(worker.getId());
                }
            }
            // na koniec usuń konto klienta
            accountsDAO.deleteAccount(account);
        }
    }
}
