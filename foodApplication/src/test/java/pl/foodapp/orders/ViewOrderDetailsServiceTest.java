package pl.foodapp.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import pl.foodapp.database.DishesEntity;
import pl.foodapp.database.OrderedDishesEntity;
import pl.foodapp.database.OrdersEntity;
import pl.foodapp.dishes.DishesDAO;
import pl.foodapp.dishes.DishesDTO;
import pl.foodapp.dishes.orderedDishes.OrderedDishesDAO;
import pl.foodapp.orders.OrdersDAO;
import pl.foodapp.orders.OrdersDTO;
import pl.foodapp.orders.ViewOrderDetailsService;

import java.math.BigDecimal;
import java.util.List;

public class ViewOrderDetailsServiceTest {

    @InjectMocks
    private ViewOrderDetailsService viewOrderDetailsService;

    @Mock
    private OrdersDAO ordersDAO;

    @Mock
    private OrderedDishesDAO orderedDishesDAO;

    @Mock
    private DishesDAO dishesDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrderDetails() {
        OrdersEntity testEntity = new OrdersEntity();
        testEntity.setOrderId(1);
        testEntity.setStatus(1);
        testEntity.setCustomer(1);
        testEntity.setTotal(BigDecimal.valueOf(1));
        testEntity.setPaymentMethod(1);
        testEntity.setStreet("a");
        testEntity.setStreetNumber(1);
        testEntity.setCity("a");
        testEntity.setApartment(1);
        testEntity.setDiscount(1);
        testEntity.setTip(BigDecimal.valueOf(1));

        when(ordersDAO.getOrderById(1)).thenReturn(testEntity);
        when(orderedDishesDAO.getDishesByOrderId(1)).thenReturn(List.of());
        when(dishesDAO.getDishById(1)).thenReturn(null);

        OrdersDTO ordersDTO = viewOrderDetailsService.getOrderDetails(1);
        assertEquals(1, ordersDTO.getOrderId());
        assertEquals(1, ordersDTO.getStatusId());
        assertEquals(1, ordersDTO.getCustomerId());
        assertEquals(BigDecimal.valueOf(1), ordersDTO.getTotal());
        assertEquals(1, ordersDTO.getPaymentMethodId());
        assertEquals("a", ordersDTO.getStreet());
        assertEquals(1, ordersDTO.getStreetNumber());
        assertEquals("a", ordersDTO.getCity());
        assertEquals(1, ordersDTO.getApartment());
        assertEquals(1, ordersDTO.getDiscountId());
        assertEquals(BigDecimal.valueOf(1), ordersDTO.getTip());
    }

    @Test
    public void testGetOrderedDishes() {
        OrdersEntity testEntity = new OrdersEntity();
        testEntity.setOrderId(1);
        testEntity.setStatus(1);
        testEntity.setCustomer(1);
        testEntity.setTotal(BigDecimal.valueOf(1));
        testEntity.setPaymentMethod(1);
        testEntity.setStreet("a");
        testEntity.setStreetNumber(1);
        testEntity.setCity("a");
        testEntity.setApartment(1);
        testEntity.setDiscount(1);
        testEntity.setTip(BigDecimal.valueOf(1));

        OrderedDishesEntity testOrderedDish = new OrderedDishesEntity();
        testOrderedDish.setDishId(1);
        testOrderedDish.setOrderId(1);
        testOrderedDish.setId(1);

        DishesEntity testDish = new DishesEntity();
        testDish.setDishId(1);
        testDish.setName("a");
        testDish.setPrice(BigDecimal.valueOf(1));
        testDish.setTypeId(1);
        testDish.setRestaurantId(1);
        testDish.setKcal(BigDecimal.valueOf(1));
        testDish.setVegetarian(true);

        when(ordersDAO.getOrderById(1)).thenReturn(testEntity);
        when(orderedDishesDAO.getDishesByOrderId(1)).thenReturn(List.of(testOrderedDish));
        when(dishesDAO.getDishById(1)).thenReturn(testDish);

        List<DishesDTO> dishesDTO = viewOrderDetailsService.getOrderedDishes(1);
        assertEquals(1, dishesDTO.size());
        assertEquals(1, dishesDTO.get(0).getDishId());
        assertEquals("a", dishesDTO.get(0).getName());
        assertEquals(BigDecimal.valueOf(1), dishesDTO.get(0).getPrice());
        assertEquals(1, dishesDTO.get(0).getTypeId());
        assertEquals(1, dishesDTO.get(0).getRestaurantId());
        assertEquals(BigDecimal.valueOf(1), dishesDTO.get(0).getKcal());
        assertEquals(true, dishesDTO.get(0).getVegetarian());
    }

    @Test
    public void testGetOrderedDishesNoDishes() {
        OrdersEntity testEntity = new OrdersEntity();
        testEntity.setOrderId(1);
        testEntity.setStatus(1);
        testEntity.setCustomer(1);
        testEntity.setTotal(BigDecimal.valueOf(1));
        testEntity.setPaymentMethod(1);
        testEntity.setStreet("a");
        testEntity.setStreetNumber(1);
        testEntity.setCity("a");
        testEntity.setApartment(1);
        testEntity.setDiscount(1);
        testEntity.setTip(BigDecimal.valueOf(1));

        when(ordersDAO.getOrderById(1)).thenReturn(testEntity);
        when(orderedDishesDAO.getDishesByOrderId(1)).thenReturn(List.of());
        when(dishesDAO.getDishById(1)).thenReturn(null);

        List<DishesDTO> dishesDTO = viewOrderDetailsService.getOrderedDishes(1);
        assertEquals(0, dishesDTO.size());
    }
}
