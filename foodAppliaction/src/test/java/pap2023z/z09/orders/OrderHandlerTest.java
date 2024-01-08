package pap2023z.z09.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderHandlerTest {
    private static int exampleNoExistentAmount = -1;
    private static BigDecimal exampleNoExistentBigAmount = new BigDecimal(-1);

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