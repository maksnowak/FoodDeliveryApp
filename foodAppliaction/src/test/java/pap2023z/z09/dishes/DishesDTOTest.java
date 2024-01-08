package pap2023z.z09.dishes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.DishesEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DishesDTOTest {
    private DishesDTO dishesDTO;

    @Mock
    private DishesEntity dishesEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        dishesDTO = new DishesDTO();
    }

    @Test
    public void testSetAndGetDishId() {
        int dishId = 1;
        dishesDTO.setDishId(dishId);
        assertEquals(dishId, dishesDTO.getDishId());
    }

    @Test
    public void testSetAndGetName() {
        String name = "test";
        dishesDTO.setName(name);
        assertEquals(name, dishesDTO.getName());
    }

    @Test
    public void testSetAndGetRestaurantId() {
        int restaurantId = 1;
        dishesDTO.setRestaurantId(restaurantId);
        assertEquals(restaurantId, dishesDTO.getRestaurantId());
    }

    @Test
    public void testSetAndGetTypeId() {
        int typeId = 1;
        dishesDTO.setTypeId(typeId);
        assertEquals(typeId, dishesDTO.getTypeId());
    }

    @Test
    public void testSetAndGetVegetarian() {
        Boolean vegetarian = true;
        dishesDTO.setVegetarian(vegetarian);
        assertEquals(vegetarian, dishesDTO.getVegetarian());
    }

    @Test
    public void testSetAndGetPrice() {
        BigDecimal price = BigDecimal.valueOf(100);
        dishesDTO.setPrice(price);
        assertEquals(price, dishesDTO.getPrice());
    }

    @Test
    public void testSetAndGetKcal() {
        BigDecimal kcal = BigDecimal.valueOf(1000);
        dishesDTO.setKcal(kcal);
        assertEquals(kcal, dishesDTO.getKcal());
    }

    @Test
    public void testFromEntity() {
        int dishId = 1;
        String name = "test";
        int restaurantId = 1;
        int typeId = 1;
        Boolean vegetarian = true;
        BigDecimal price = BigDecimal.valueOf(100);
        BigDecimal kcal = BigDecimal.valueOf(1000);
        when(dishesEntity.getDishId()).thenReturn(dishId);
        when(dishesEntity.getName()).thenReturn(name);
        when(dishesEntity.getRestaurantId()).thenReturn(restaurantId);
        when(dishesEntity.getTypeId()).thenReturn(typeId);
        when(dishesEntity.isVegetarian()).thenReturn(vegetarian);
        when(dishesEntity.getPrice()).thenReturn(price);
        when(dishesEntity.getKcal()).thenReturn(kcal);
        DishesDTO dishesDTO = DishesDTO.fromEntity(dishesEntity);
        assertEquals(dishId, dishesDTO.getDishId());
        assertEquals(name, dishesDTO.getName());
        assertEquals(restaurantId, dishesDTO.getRestaurantId());
        assertEquals(typeId, dishesDTO.getTypeId());
        assertEquals(vegetarian, dishesDTO.getVegetarian());
        assertEquals(price, dishesDTO.getPrice());
        assertEquals(kcal, dishesDTO.getKcal());
    }



}
