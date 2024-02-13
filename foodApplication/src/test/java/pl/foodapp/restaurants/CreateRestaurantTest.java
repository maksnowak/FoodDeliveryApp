package pl.foodapp.restaurants;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.restaurants.AddRestaurant;
import pl.foodapp.restaurants.RestaurantsDAO;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

public class CreateRestaurantTest {
    @InjectMocks
    private AddRestaurant createRestaurant;

    @Mock
    private RestaurantsDAO restaurantsDAO;

    @Mock
    private RestaurantsEntity restaurant;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void validateInputTest() {
        assertThrows(IllegalArgumentException.class, () -> createRestaurant.validateInput(null,  Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), Time.valueOf("00:00:00")));
        assertThrows(IllegalArgumentException.class, () -> createRestaurant.validateInput("", Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), Time.valueOf("00:00:00")));
        assertThrows(IllegalArgumentException.class, () -> createRestaurant.validateInput( "name", null, Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), Time.valueOf("00:00:00")));
        assertThrows(IllegalArgumentException.class, () -> createRestaurant.validateInput( "name", Time.valueOf("00:00:00"), null, Time.valueOf("00:00:00"), Time.valueOf("00:00:00")));
        assertThrows(IllegalArgumentException.class, () -> createRestaurant.validateInput( "name", Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), null, Time.valueOf("00:00:00")));
        assertThrows(IllegalArgumentException.class, () -> createRestaurant.validateInput( "name", Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), null));
    }

}
