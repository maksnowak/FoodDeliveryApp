package pl.foodapp.dishes.orderedDishes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import pl.foodapp.database.OrderedDishesEntity;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDTO;

public class OrderedDishesDTOTest {
    private OrderedDishesDTO orderedDishDTO;
    public static int exampleInt = 1;
    @Mock
    private OrderedDishesEntity orderEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderedDishDTO = new OrderedDishesDTO();
    }

    @Test
    public void testSetAndGetId() {

        orderedDishDTO.setId(exampleInt);
        assertEquals(exampleInt, orderedDishDTO.getId());
    }

    @Test
    public void testSetAndGetDishId() {

        orderedDishDTO.setDishId(exampleInt);
        assertEquals(exampleInt, orderedDishDTO.getDishId());
    }

    @Test
    public void testSetAndGetOrderId() {

        orderedDishDTO.setOrderId(exampleInt);
        assertEquals(exampleInt, orderedDishDTO.getOrderId());
    }

    @Test
    public void testDTOFromEntity() {

        when(orderEntity.getId()).thenReturn(exampleInt);
        when(orderEntity.getOrderId()).thenReturn(exampleInt);
        when(orderEntity.getDishId()).thenReturn(exampleInt);

        orderedDishDTO = new OrderedDishesDTO().fromEntity(orderEntity);

        assertEquals(exampleInt, orderedDishDTO.getId());
        assertEquals(exampleInt, orderedDishDTO.getOrderId());
        assertEquals(exampleInt, orderedDishDTO.getDishId());

    }
}
