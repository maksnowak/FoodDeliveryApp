package pap2023z.z09.dishes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.DishesEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AddDishTest {
    @InjectMocks
    private AddDish addDish;

    @Mock
    private DishesDAO dishesDAO;

    @Mock
    private DishesEntity dishesEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addDishSuccessTest() {
        DishesDTO dto = new DishesDTO(1, "name", 1, 1, true, null, null);
        doNothing().when(dishesDAO).addDish(any());
        addDish.addDish(dto);
        verify(dishesDAO, times(1)).addDish(any());
    }

}
