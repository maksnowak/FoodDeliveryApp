package pap2023z.z09.discounts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

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
    public void checkDiscountRange(){
        assertThrows(IllegalArgumentException.class, () -> adder.checkIfInRange(new BigDecimal(exampleBadInt)));
        assertDoesNotThrow(() -> adder.checkIfInRange(new BigDecimal(exampleInt)));
    }
    @Test
    public void checkCode(){
        assertThrows(IllegalArgumentException.class, () -> adder.checkCode(exampleBadString));
        assertDoesNotThrow(() -> adder.checkCode(exampleString));
}}

/*

package pap2023z.z09.reviews;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class AddReviewsTest {
    @InjectMocks
    private AddReviews adder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    private final static int exampleOutOfRangeInt = -1;
    private final static int exampleOutOfRangeInt2 = 6;
    private final static int exampleInt = 5;
    private final static int exampleInt2 = 0;
    @Test
    public void checkStarsRange(){
        assertThrows(IllegalArgumentException.class, () -> adder.checkIfStarsInRange(exampleOutOfRangeInt));
        assertThrows(IllegalArgumentException.class, () -> adder.checkIfStarsInRange(exampleOutOfRangeInt2));
        assertDoesNotThrow(() -> adder.checkIfStarsInRange(exampleInt));
        assertDoesNotThrow(() -> adder.checkIfStarsInRange(exampleInt2));

    }

}

 */