package pap2023z.z09.gui;

import pap2023z.z09.accounts.EmailAlreadyExistsException;
import pap2023z.z09.database.AccountsEntity;
import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.dishes.DishesDTO;
public interface Callback {
    void onRestaurantSelected(RestaurantsEntity restaurant);
    void onAccountLogged(AccountsEntity account);
    void onEditAccount(String name, String surname, String email, String password) throws EmailAlreadyExistsException;
    void onDeleteAccount();
    void onAccountLogout();
    void addToBasket(DishesDTO dish);
    void enterBasket();
}
