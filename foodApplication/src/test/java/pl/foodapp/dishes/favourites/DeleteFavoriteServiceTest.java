package pl.foodapp.dishes.favourites;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.database.FavoritesEntity;
import pl.foodapp.dishes.favourites.DeleteFavoriteService;
import pl.foodapp.dishes.favourites.FavoritesDAO;

import static org.mockito.Mockito.*;

public class DeleteFavoriteServiceTest {
    @InjectMocks
    private DeleteFavoriteService deleteFavoriteService;

    @Mock
    private FavoritesDAO favoritesDAO;

    @Mock
    private FavoritesEntity favoritesEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteFavorite() {
        int id = 1;
        when(favoritesDAO.getFavoriteById(id)).thenReturn(favoritesEntity);
        deleteFavoriteService.deleteFavorite(id);
        verify(favoritesDAO, times(1)).deleteFavorite(any());
    }

    @Test
    public void testDeleteFavoriteNull() {
        int id = 1;
        when(favoritesDAO.getFavoriteById(id)).thenReturn(null);
        deleteFavoriteService.deleteFavorite(id);
        verify(favoritesDAO, times(0)).deleteFavorite(any());
    }
}
