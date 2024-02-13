package pl.foodapp.dishes.favourites;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.database.FavoritesEntity;
import pl.foodapp.dishes.favourites.AddFavoriteService;
import pl.foodapp.dishes.favourites.FavoritesDAO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddFavoriteServiceTest {
    @InjectMocks
    private AddFavoriteService addFavoriteService;

    @Mock
    private FavoritesDAO favoritesDAO;

    @Mock
    private FavoritesEntity favoritesEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFavorite() {
        int dishId = 1;
        int customerId = 1;
        when(favoritesDAO.getFavoritesByCustomer(customerId)).thenReturn(List.of());
        addFavoriteService.addFavorite(dishId, customerId);
        verify(favoritesDAO, times(1)).addFavorite(any());
    }

    @Test
    public void testAddFavoriteAlreadyInFavorites() {
        int dishId = 1;
        int customerId = 1;
        when(favoritesDAO.getFavoritesByCustomer(customerId)).thenReturn(List.of(favoritesEntity));
        when(favoritesEntity.getDishId()).thenReturn(dishId);
        when(favoritesEntity.getCustomer()).thenReturn(customerId);
        assertThrows(IllegalArgumentException.class, () -> addFavoriteService.addFavorite(dishId, customerId));
    }
}
