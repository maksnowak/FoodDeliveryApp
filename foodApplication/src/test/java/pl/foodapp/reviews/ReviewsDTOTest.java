package pl.foodapp.reviews;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.MockitoAnnotations;
import pl.foodapp.reviews.ReviewsDTO;

public class ReviewsDTOTest {

    private ReviewsDTO reviewsDTO;
    public static int exampleInt = 1;
    public static String exampleString = "a";
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reviewsDTO = new ReviewsDTO();
    }


    @Test
    public void testSetAndGetReviewId() {

        reviewsDTO.setReviewId(exampleInt);
        assertEquals(exampleInt, reviewsDTO.getReviewId());
    }
    @Test
    public void testSetAndGetRestaurantId() {
        reviewsDTO.setRestaurantId(exampleInt);
        assertEquals(exampleInt, reviewsDTO.getRestaurantId());
    }
    @Test
    public void testSetAndGetCustomerId() {
        reviewsDTO.setCustomer(exampleInt);
        assertEquals(exampleInt, reviewsDTO.getCustomer());
    }

    @Test
    public void testSetAndGetStars() {
        reviewsDTO.setStars(exampleInt);
        assertEquals(exampleInt, reviewsDTO.getStars());
    }

    @Test
    public void testSetAndGetDescription() {
        reviewsDTO.setDescription(exampleString);
        assertEquals(exampleString, reviewsDTO.getDescription());
    }
}