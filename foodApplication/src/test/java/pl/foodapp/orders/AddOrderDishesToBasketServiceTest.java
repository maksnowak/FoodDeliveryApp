package pl.foodapp.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.baskets.AddBasket;
import pl.foodapp.database.OrdersEntity;
import pl.foodapp.dishes.DishesDTO;
import pl.foodapp.orders.AddOrderDishesToBasketService;
import pl.foodapp.orders.OrdersDAO;
import pl.foodapp.orders.ViewOrderDetailsService;

import static org.mockito.Mockito.*;

import java.util.List;

public class AddOrderDishesToBasketServiceTest {
    @InjectMocks
    private AddOrderDishesToBasketService addOrderDishesToBasketService;

    @Mock
    private OrdersDAO ordersDAO;

    @Mock
    private ViewOrderDetailsService viewOrderDetailsService;

    @Mock
    private AddBasket addBasket;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addOrderDishesToBasket() {
        OrdersEntity entity = new OrdersEntity();
        entity.setCustomer(1);

        DishesDTO dto = new DishesDTO();
        dto.setDishId(1);

        List<DishesDTO> dishes = List.of(dto);

        when(ordersDAO.getOrderById(1)).thenReturn(entity);
        when(viewOrderDetailsService.getOrderedDishes(1)).thenReturn(dishes);

        addOrderDishesToBasketService.addOrderDishesToBasket(1);

        verify(addBasket, times(1)).addBasket(any());
    }

    @Test
    void addOrderDishesToBasketEmpty() {
        OrdersEntity entity = new OrdersEntity();
        entity.setCustomer(1);

        List<DishesDTO> dishes = List.of();

        when(ordersDAO.getOrderById(1)).thenReturn(entity);
        when(viewOrderDetailsService.getOrderedDishes(1)).thenReturn(dishes);

        addOrderDishesToBasketService.addOrderDishesToBasket(1);

        verify(addBasket, times(0)).addBasket(any());
    }
}
