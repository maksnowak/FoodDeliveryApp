package pap2023z.z09.dishes.favourites;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.FavoritesEntity;
import pap2023z.z09.dishes.favourites.FavoritesDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FavoritesDTOTest {
    private FavoritesDTO favoritesDTO;

    @Mock
    private FavoritesEntity favoritesEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        favoritesDTO = new FavoritesDTO();
    }

    @Test
    public void testSetAndGetId() {
        int id = 1;
        favoritesDTO.setId(id);
        assertEquals(id, favoritesDTO.getId());
    }

    @Test
    public void testSetAndGetDishId() {
        int dishId = 1;
        favoritesDTO.setDishId(dishId);
        assertEquals(dishId, favoritesDTO.getDishId());
    }

    @Test
    public void testSetAndGetCustomer() {
        int customer = 1;
        favoritesDTO.setCustomer(customer);
        assertEquals(customer, favoritesDTO.getCustomer());
    }

    @Test
    public void testFromEntity() {
        int id = 1;
        int dishId = 1;
        int customer = 1;
        when(favoritesEntity.getId()).thenReturn(id);
        when(favoritesEntity.getDishId()).thenReturn(dishId);
        when(favoritesEntity.getCustomer()).thenReturn(customer);
        FavoritesDTO favoritesDTO = FavoritesDTO.fromEntity(favoritesEntity);
        assertEquals(id, favoritesDTO.getId());
        assertEquals(dishId, favoritesDTO.getDishId());
        assertEquals(customer, favoritesDTO.getCustomer());
    }
}
