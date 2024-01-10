package pap2023z.z09.restaurants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.baskets.BasketsDAO;
import pap2023z.z09.database.DishesEntity;
import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.dishes.DishesDAO;
import pap2023z.z09.dishes.RemoveDish;
import pap2023z.z09.dishes.orderedDishes.OrderedDishesDAO;
import pap2023z.z09.dishes.favourites.FavoritesDAO;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveRestaurantTest {
    @InjectMocks
    private RemoveRestaurant removeRestaurant;

    @Mock
    private RestaurantsDAO restaurantsDAO;

    @Mock
    private DishesDAO dishesDAO;

    @Mock
    private OrderedDishesDAO orderedDishesDAO;

    @Mock
    private BasketsDAO basketsDAO;

    @Mock
    private FavoritesDAO favoritesDAO;

    @Mock
    private RemoveDish removeDish;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void removeRestaurantTest() {
        // TODO: zaimplementować test
    }
}
