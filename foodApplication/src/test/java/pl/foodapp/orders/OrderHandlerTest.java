package pl.foodapp.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import pl.foodapp.orders.OrderHandler;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderHandlerTest {
    private final static int exampleNoExistentAmount = -1;
    private final static BigDecimal exampleNoExistentBigAmount = new BigDecimal(-1);

    @InjectMocks
    private OrderHandler order;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void checkRange(){
        assertThrows(IllegalArgumentException.class, () -> order.checkIfInRange(exampleNoExistentAmount));
        assertThrows(IllegalArgumentException.class, () -> order.checkIfInRange(exampleNoExistentBigAmount));
    }

}