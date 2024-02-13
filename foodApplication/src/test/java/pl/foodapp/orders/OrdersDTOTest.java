package pl.foodapp.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import pl.foodapp.database.OrdersEntity;
import pl.foodapp.orders.OrdersDTO;

import java.math.BigDecimal;

public class OrdersDTOTest {
    private OrdersDTO orderDTO;
    public static int exampleInt = 1;
    public static String exampleString = "a";
    @Mock
    private OrdersEntity orderEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderDTO = new OrdersDTO();
    }

    @Test
    public void testSetAndGetOrderId() {

        orderDTO.setOrderId(exampleInt);
        assertEquals(exampleInt, orderDTO.getOrderId());
    }
    @Test
    public void testSetAndGetStatusId() {

        orderDTO.setStatusId(exampleInt);
        assertEquals(exampleInt, orderDTO.getStatusId());
    }
    @Test
    public void testSetAndGetCustomerId() {

        orderDTO.setCustomerId(exampleInt);
        assertEquals(exampleInt, orderDTO.getCustomerId());
    }
    @Test
    public void testSetAndGetTotal() {
        orderDTO.setTotal(new BigDecimal(exampleInt));
        assertEquals(new BigDecimal(exampleInt), orderDTO.getTotal());
    }
    @Test
    public void testSetAndGetPaymentMethodId() {
        orderDTO.setPaymentMethodId(exampleInt);
        assertEquals(exampleInt, orderDTO.getPaymentMethodId());
    }
    @Test
    public void testSetAndGetStreet() {
        orderDTO.setStreet(exampleString);
        assertEquals(exampleString, orderDTO.getStreet());
    }
    @Test
    public void testSetAndGetStreetNumber() {
        orderDTO.setStreetNumber(exampleInt);
        assertEquals(exampleInt, orderDTO.getStreetNumber());
    }
    @Test
    public void testSetAndGetApartment() {
        orderDTO.setApartment(exampleInt);
        assertEquals(exampleInt, orderDTO.getApartment());
    }
    @Test
    public void testSetAndGetCity() {
        orderDTO.setCity(exampleString);
        assertEquals(exampleString, orderDTO.getCity());
    }
    @Test
    public void testSetAndGetDiscountId() {
        orderDTO.setDiscountId(exampleInt);
        assertEquals(exampleInt, orderDTO.getDiscountId());
    }
    @Test
    public void testSetAndGetTip() {
        orderDTO.setTip(new BigDecimal(exampleInt));
        assertEquals(new BigDecimal(exampleInt), orderDTO.getTip());


    }

    @Test
    public void testDTOFromEntity() {

        OrdersEntity entity = new OrdersEntity();

        when(orderEntity.getOrderId()).thenReturn(exampleInt);
        when(orderEntity.getStatus()).thenReturn(exampleInt);
        when(orderEntity.getCustomer()).thenReturn(exampleInt);
        when(orderEntity.getTotal()).thenReturn(new BigDecimal(exampleInt));
        when(orderEntity.getPaymentMethod()).thenReturn(exampleInt);
        when(orderEntity.getStreet()).thenReturn(exampleString);
        when(orderEntity.getStreetNumber()).thenReturn(exampleInt);
        when(orderEntity.getApartment()).thenReturn(exampleInt);
        when(orderEntity.getCity()).thenReturn(exampleString);
        when(orderEntity.getDiscount()).thenReturn(exampleInt);
        when(orderEntity.getTip()).thenReturn(new BigDecimal(exampleInt));

        orderDTO = new OrdersDTO().fromEntity(orderEntity);

        assertEquals(exampleInt, orderDTO.getOrderId());
        assertEquals(exampleInt, orderDTO.getStatusId());
        assertEquals(exampleInt, orderDTO.getCustomerId());
        assertEquals(new BigDecimal(exampleInt), orderDTO.getTotal());
        assertEquals(exampleInt, orderDTO.getPaymentMethodId());
        assertEquals(exampleString, orderDTO.getStreet());
        assertEquals(exampleInt, orderDTO.getStreetNumber());
        assertEquals(exampleInt, orderDTO.getApartment());
        assertEquals(exampleString, orderDTO.getCity());
        assertEquals(exampleInt, orderDTO.getDiscountId());
        assertEquals(new BigDecimal(exampleInt), orderDTO.getTip());
    }
}