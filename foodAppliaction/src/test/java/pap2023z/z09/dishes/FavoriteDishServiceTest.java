package pap2023z.z09.dishes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.FavoritesEntity;
import pap2023z.z09.database.DishesEntity;

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

    //TODO: poprawić testy żeby sprawdzały cały obiekt

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
}