package pl.foodapp.restaurants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.restaurants.AddRestaurant;
import pl.foodapp.restaurants.InvalidTimeException;
import pl.foodapp.restaurants.RestaurantsDAO;
import pl.foodapp.restaurants.RestaurantsDTO;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
public class AddRestaurantTest {
    @InjectMocks
    private AddRestaurant addRestaurant;

    @Mock
    private RestaurantsDAO restaurantsDAO;

    @Mock
    private RestaurantsEntity restaurant;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addRestaurantSuccessTest() throws InvalidTimeException {
        when(restaurantsDAO.addRestaurant(any())).thenReturn(1);
        addRestaurant.addRestaurant(new RestaurantsDTO(1, "name", Time.valueOf("10:00:00"), Time.valueOf("22:00:00"), Time.valueOf("12:00:00"), Time.valueOf("24:00:00")));
        verify(restaurantsDAO, times(1)).addRestaurant(any());
    }

    @Test
    public void addRestaurantFailWeekdaysTest() {
        assertThrows(InvalidTimeException.class, () -> addRestaurant.addRestaurant(new RestaurantsDTO(1, "name", Time.valueOf("22:00:00"), Time.valueOf("10:00:00"), Time.valueOf("12:00:00"), Time.valueOf("24:00:00"))));
        verify(restaurantsDAO, times(0)).addRestaurant(any());
    }

    @Test
    public void addRestaurantFailWeekendsTest() {
        assertThrows(InvalidTimeException.class, () -> addRestaurant.addRestaurant(new RestaurantsDTO(1, "name", Time.valueOf("10:00:00"), Time.valueOf("22:00:00"), Time.valueOf("12:00:00"), Time.valueOf("10:00:00"))));
        verify(restaurantsDAO, times(0)).addRestaurant(any());
    }

    @Test
    public void validateInputTest() {
        assertThrows(IllegalArgumentException.class, () -> addRestaurant.validateInput(null,  Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), Time.valueOf("00:00:00")));
        assertThrows(IllegalArgumentException.class, () -> addRestaurant.validateInput("", Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), Time.valueOf("00:00:00")));
        assertThrows(IllegalArgumentException.class, () -> addRestaurant.validateInput( "name", null, Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), Time.valueOf("00:00:00")));
        assertThrows(IllegalArgumentException.class, () -> addRestaurant.validateInput( "name", Time.valueOf("00:00:00"), null, Time.valueOf("00:00:00"), Time.valueOf("00:00:00")));
        assertThrows(IllegalArgumentException.class, () -> addRestaurant.validateInput( "name", Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), null, Time.valueOf("00:00:00")));
        assertThrows(IllegalArgumentException.class, () -> addRestaurant.validateInput( "name", Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), Time.valueOf("00:00:00"), null));
    }

    @Test
    public void verifyWeekdaysTest() throws InvalidTimeException {
        assertThrows(InvalidTimeException.class, () -> addRestaurant.verifyWeekdays(new RestaurantsDTO(1, "name", Time.valueOf("22:00:00"), Time.valueOf("10:00:00"), Time.valueOf("12:00:00"), Time.valueOf("24:00:00"))));
    }

    @Test
    public void verifyWeekendsTest() throws InvalidTimeException {
        assertThrows(InvalidTimeException.class, () -> addRestaurant.verifyWeekends(new RestaurantsDTO(1, "name", Time.valueOf("10:00:00"), Time.valueOf("22:00:00"), Time.valueOf("12:00:00"), Time.valueOf("10:00:00"))));
    }

    @Test
    public void verifySameHourWeekendsTest() throws InvalidTimeException {
        assertThrows(InvalidTimeException.class, () -> addRestaurant.verifyWeekends(new RestaurantsDTO(1, "name", Time.valueOf("10:00:00"), Time.valueOf("22:00:00"), Time.valueOf("12:00:00"), Time.valueOf("12:00:00"))));
    }

    @Test
    public void verifyWeekdaysSuccessTest() throws InvalidTimeException {
        addRestaurant.verifyWeekdays(new RestaurantsDTO(1, "name", Time.valueOf("10:00:00"), Time.valueOf("22:00:00"), Time.valueOf("12:00:00"), Time.valueOf("24:00:00")));
    }

    @Test
    public void verifyWeekendsSuccessTest() throws InvalidTimeException {
        addRestaurant.verifyWeekends(new RestaurantsDTO(1, "name", Time.valueOf("10:00:00"), Time.valueOf("22:00:00"), Time.valueOf("12:00:00"), Time.valueOf("24:00:00")));
    }

    @Test
    public void verifySameHourWeekdaysSuccessTest() throws InvalidTimeException {
        addRestaurant.verifyWeekdays(new RestaurantsDTO(1, "name", Time.valueOf("10:00:00"), Time.valueOf("22:00:00"), Time.valueOf("12:00:00"), Time.valueOf("12:00:00")));
    }



}
