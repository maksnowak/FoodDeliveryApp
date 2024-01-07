package pap2023z.z09.dishes.orderedDishes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import pap2023z.z09.database.OrderedDishesEntity;
import pap2023z.z09.dishes.orderedDishes.OrderedDishsesDTO;

public class OrderedDishesDTOTest {
    private OrderedDishsesDTO orderedDishDTO;
    public static int exampleInt = 1;
    @Mock
    private OrderedDishesEntity orderEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderedDishDTO = new OrderedDishsesDTO();
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

        when(orderEntity.getOrderId()).thenReturn(exampleInt);
        when(orderEntity.getDishId()).thenReturn(exampleInt);

        orderedDishDTO = new OrderedDishsesDTO().fromEntity(orderEntity);

        assertEquals(exampleInt, orderedDishDTO.getOrderId());
        assertEquals(exampleInt, orderedDishDTO.getDishId());

    }
}
