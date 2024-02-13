package pl.foodapp.dishes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.baskets.BasketsDAO;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.RemoveDish;
import pl.foodapp.dishes.favourites.FavoritesDAO;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDAO;

import static org.mockito.Mockito.*;

public class RemoveDishTest {
    @InjectMocks
    private RemoveDish removeDish;

    @Mock
    private DishesDAO dishesDAO;

    @Mock
    private OrderedDishesDAO orderedDishesDAO;

    @Mock
    private BasketsDAO basketsDAO;

    @Mock
    private FavoritesDAO favoritesDAO;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void removeDishSuccessTest() {
        int dishId = 1;
        doNothing().when(dishesDAO).removeDish(anyInt());
        doNothing().when(orderedDishesDAO).deleteDish(any());
        doNothing().when(basketsDAO).deleteBasket(any());
        doNothing().when(favoritesDAO).deleteFavorite(any());
        removeDish.removeDish(dishId);
        verify(dishesDAO, times(1)).removeDish(dishId);
    }
}
