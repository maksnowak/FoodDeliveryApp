package pl.foodapp.dishes.favourites;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.database.FavoritesEntity;
import pl.foodapp.database.DishesEntity;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.DishesDTO;
import pl.foodapp.dishes.favourites.FavoriteDishService;
import pl.foodapp.dishes.favourites.FavoritesDAO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FavoriteDishServiceTest {
    @InjectMocks
    private FavoriteDishService favoriteDishService;

    @Mock
    private FavoritesDAO favoritesDAO;

    @Mock
    private DishesDAO dishesDAO;

    @Mock
    private FavoritesEntity favoritesEntity;

    @Mock
    private DishesEntity dishesEntity;

    @Mock
    private DishesDTO dishesDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFavoriteDish() {
        int id = 1;
        when(favoritesDAO.getFavoriteById(id)).thenReturn(favoritesEntity);
        when(favoritesEntity.getDishId()).thenReturn(id);
        when(dishesDAO.getDishById(id)).thenReturn(dishesEntity);
        when(dishesEntity.getDishId()).thenReturn(id);
        when(dishesDTO.getDishId()).thenReturn(id);
        assertEquals(dishesDTO.getDishId(), favoriteDishService.getFavoriteDish(id).getDishId());
    }

    @Test
    public void testGetFavoriteDishNull() {
        int id = 1;
        when(favoritesDAO.getFavoriteById(id)).thenReturn(null);
        assertNull(favoriteDishService.getFavoriteDish(id));
    }

    @Test
    public void testGetAllCustomerFavorites() {
        int customer = 1;
        when(favoritesDAO.getFavoritesByCustomer(customer)).thenReturn(List.of(favoritesEntity));
        when(favoritesEntity.getCustomer()).thenReturn(customer);
        assertEquals(1, favoriteDishService.getAllCustomerFavorites(customer).size());
    }

    @Test
    public void testGetAllCustomerFavoritesEmpty() {
        int customer = 1;
        when(favoritesDAO.getFavoritesByCustomer(customer)).thenReturn(List.of());
        assertEquals(0, favoriteDishService.getAllCustomerFavorites(customer).size());
    }
}