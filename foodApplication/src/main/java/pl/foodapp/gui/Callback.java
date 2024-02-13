package pl.foodapp.gui;

import pl.foodapp.accounts.EmailAlreadyExistsException;
import pl.foodapp.database.AccountsEntity;
import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.dishes.DishesDTO;

public interface Callback {
    void onRestaurantSelected(RestaurantsEntity restaurant);
    void onFavouritesRestaurantSelected(RestaurantsEntity restaurant);

    void onAccountLogged(AccountsEntity account);
    void onEditAccount(String name, String surname, String email, String password) throws EmailAlreadyExistsException;
    void onDeleteAccount();
    void onAccountLogout();
    void addToBasket(DishesDTO dish);
    void addToFavourites(DishesDTO dish);

    void enterBasket();
    void onOpinionRestaurantSelected(RestaurantsEntity restaurant);
    void enterPaymentMethods();
    void enterPayment();
    void enterModifyPaymentMethod(int id);
    void enterModifyRestaurantPanel();
    void enterHistoryPanel();
    void enterOrderDetailsPanel(int orderId);
    void enterComplaintPanel(int orderId);
    void enterRestaurantStats(int restaurantId);
    void enterRestaurantChoicePanel();
}
