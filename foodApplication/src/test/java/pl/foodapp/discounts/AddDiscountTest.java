package pl.foodapp.discounts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import pl.foodapp.discounts.AddDiscount;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AddDiscountTest {

    @InjectMocks
    private AddDiscount adder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    private final static int exampleBadInt = -1;
    private final static int exampleInt = 0;
    private final static String exampleString = "review";
    private final static String exampleBadString = "";

    @Test
    public void testcheckDiscountRange(){
        assertThrows(IllegalArgumentException.class, () -> adder.checkIfInRange(new BigDecimal(exampleBadInt)));
        assertDoesNotThrow(() -> adder.checkIfInRange(new BigDecimal(exampleInt)));
    }
    @Test
    public void testcheckCode(){
        assertThrows(IllegalArgumentException.class, () -> adder.checkCode(exampleBadString));
        assertDoesNotThrow(() -> adder.checkCode(exampleString));
    }
}
