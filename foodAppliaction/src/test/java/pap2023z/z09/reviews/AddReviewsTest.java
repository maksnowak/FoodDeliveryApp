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