package pl.foodapp.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import pl.foodapp.database.OrdersEntity;
import pl.foodapp.orders.OrdersDAO;
import pl.foodapp.orders.OrdersDTO;
import pl.foodapp.orders.ViewAccountsOrderHistoryService;

import java.math.BigDecimal;
import java.util.List;

public class ViewAccountsOrderHistoryServiceTest {

    @InjectMocks
    private ViewAccountsOrderHistoryService viewAccountsOrderHistoryService;

    @Mock
    private OrdersDAO ordersDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrdersHistory() {
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

        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of(testEntity));
        List<OrdersDTO> ordersDTO = viewAccountsOrderHistoryService.getOrdersHistory(1);
        assertEquals(1, ordersDTO.size());
        assertEquals(1, ordersDTO.getFirst().getOrderId());
        assertEquals(1, ordersDTO.getFirst().getStatusId());
        assertEquals(1, ordersDTO.getFirst().getCustomerId());
        assertEquals(BigDecimal.valueOf(1), ordersDTO.getFirst().getTotal());
        assertEquals(1, ordersDTO.getFirst().getPaymentMethodId());
        assertEquals("a", ordersDTO.getFirst().getStreet());
        assertEquals(1, ordersDTO.getFirst().getStreetNumber());
        assertEquals("a", ordersDTO.getFirst().getCity());
        assertEquals(1, ordersDTO.getFirst().getApartment());
        assertEquals(1, ordersDTO.getFirst().getDiscountId());
        assertEquals(BigDecimal.valueOf(1), ordersDTO.getFirst().getTip());
    }

    @Test
    public void testGetOrdersHistoryEmpty() {
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of());
        List<OrdersDTO> ordersDTO = viewAccountsOrderHistoryService.getOrdersHistory(1);
        assertEquals(0, ordersDTO.size());
    }
}
