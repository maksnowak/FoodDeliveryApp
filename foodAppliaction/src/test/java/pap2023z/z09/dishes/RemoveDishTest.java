package pap2023z.z09.dishes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class RemoveDishTest {
    @InjectMocks
    private RemoveDish removeDish;

    @Mock
    private DishesDAO dishesDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void removeDishSuccessTest() {
        int dishId = 1;
        doNothing().when(dishesDAO).removeDish(anyInt());
        removeDish.removeDish(dishId);
        verify(dishesDAO, times(1)).removeDish(dishId);
    }
}
