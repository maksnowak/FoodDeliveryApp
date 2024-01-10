package pap2023z.z09.dishes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.baskets.BasketsDAO;
import pap2023z.z09.dishes.favourites.FavoritesDAO;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;

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
