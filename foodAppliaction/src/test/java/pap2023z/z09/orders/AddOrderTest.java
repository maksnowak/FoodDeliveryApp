package pap2023z.z09.orders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddOrderTest {
    private static int exampleNoExistentId = -1;
    private static int exampleNoExistentAmount = -1;
    private static BigDecimal exampleNoExistentBigAmount = new BigDecimal(-1);

    @InjectMocks
    private AddOrder order;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void checkDiscountIdTest(){
        assertThrows(IllegalArgumentException.class, () -> order.checkDiscountId(exampleNoExistentId));
    }

    @Test
    public void checkCustomerIdTest(){
        assertThrows(IllegalArgumentException.class, () -> order.checkCustomerId(exampleNoExistentId));
    }

    @Test
    public void checkPaymentMethodIdTest(){
        assertThrows(IllegalArgumentException.class, () -> order.checkPaymentMethodId(exampleNoExistentId));
    }
    @Test
    public void checkRange(){
        assertThrows(IllegalArgumentException.class, () -> order.checkIfInRange(exampleNoExistentAmount));
        assertThrows(IllegalArgumentException.class, () -> order.checkIfInRange(exampleNoExistentBigAmount));

    }

}