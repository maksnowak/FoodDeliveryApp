package pap2023z.z09.complaints;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.discounts.AddDiscount;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
public class AddComplaintTest {

    @InjectMocks
    private AddComplaint complaint;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    private final static String exampleString = "review";
    private final static String exampleBadString = "";

    @Test
    public void testcheckDescription(){
        assertThrows(IllegalArgumentException.class, () -> complaint.checkDescription(exampleBadString));
        assertDoesNotThrow(() -> complaint.checkDescription(exampleString));
    }

}

/*


public class AddDiscountTest {


    private final static int exampleBadInt = -1;
    private final static int exampleInt = 0;
    private final static String exampleString = "review";
    private final static String exampleBadString = "";

    @Test
    public void checkDiscountRange(){
        assertThrows(IllegalArgumentException.class, () -> adder.checkIfInRange(new BigDecimal(exampleBadInt)));
        assertDoesNotThrow(() -> adder.checkIfInRange(new BigDecimal(exampleInt)));
    }
    @Test
    public void checkCode(){
        assertThrows(IllegalArgumentException.class, () -> adder.checkCode(exampleBadString));
        assertDoesNotThrow(() -> adder.checkCode(exampleString));
    }
}

 */