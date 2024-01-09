package pap2023z.z09.accounts;

import pap2023z.z09.baskets.BasketsDAO;
import pap2023z.z09.database.*;
import pap2023z.z09.dishes.favourites.DeleteFavoriteService;
import pap2023z.z09.dishes.favourites.FavoritesDAO;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;
import pap2023z.z09.orders.OrdersDAO;
import pap2023z.z09.paymentMethods.DeleteCreditCardService;
import pap2023z.z09.paymentMethods.PaymentMethodsDAO;


import java.util.List;

public class DeleteService {
    private final AccountsDAO accountsDAO;
    private final OrdersDAO ordersDAO;
    private final PaymentMethodsDAO paymentMethodsDAO;
    private final FavoritesDAO favoritesDAO;
    private final BasketsDAO basketsDAO;

    public DeleteService(AccountsDAO accountsDAO, OrdersDAO ordersDAO, PaymentMethodsDAO paymentMethodsDAO, FavoritesDAO favoritesDAO, BasketsDAO basketsDAO) {
        this.accountsDAO = accountsDAO;
        this.ordersDAO = ordersDAO;
        this.paymentMethodsDAO = paymentMethodsDAO;
        this.favoritesDAO = favoritesDAO;
        this.basketsDAO = basketsDAO;
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
            // na koniec usuń konto klienta
            accountsDAO.deleteAccount(account);
        }
    }
}
