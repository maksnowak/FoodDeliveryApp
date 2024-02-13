package pl.foodapp.restaurants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.restaurants.RestaurantsDTO;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantsDTOTest {
    private RestaurantsDTO restaurantsDTO;

    @Mock
    private RestaurantsEntity restaurantsEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantsDTO = new RestaurantsDTO();
    }

    @Test
    public void testSetAndGetRestaurantId() {
        int restaurantId = 1;
        restaurantsDTO.setRestaurantId(restaurantId);
        assertEquals(restaurantId, restaurantsDTO.getRestaurantId());
    }

    @Test
    public void testSetAndGetName() {
        String name = "test";
        restaurantsDTO.setName(name);
        assertEquals(name, restaurantsDTO.getName());
    }

    @Test
    public void testSetAndGetOpensWeekdays() {
        Time opensWeekdays = Time.valueOf("10:00:00");
        restaurantsDTO.setOpensWeekdays(opensWeekdays);
        assertEquals(opensWeekdays, restaurantsDTO.getOpensWeekdays());
    }

    @Test
    public void testSetAndGetClosesWeekdays() {
        Time closesWeekdays = Time.valueOf("20:00:00");
        restaurantsDTO.setClosesWeekdays(closesWeekdays);
        assertEquals(closesWeekdays, restaurantsDTO.getClosesWeekdays());
    }

    @Test
    public void testSetAndGetOpensWeekends() {
        Time opensWeekends = Time.valueOf("10:00:00");
        restaurantsDTO.setOpensWeekends(opensWeekends);
        assertEquals(opensWeekends, restaurantsDTO.getOpensWeekends());
    }

    @Test
    public void testSetAndGetClosesWeekends() {
        Time closesWeekends = Time.valueOf("20:00:00");
        restaurantsDTO.setClosesWeekends(closesWeekends);
        assertEquals(closesWeekends, restaurantsDTO.getClosesWeekends());
    }

    @Test
    public void testFromEntity() {
        int restaurantId = 1;
        String name = "test";
        Time opensWeekdays = Time.valueOf("10:00:00");
        Time closesWeekdays = Time.valueOf("20:00:00");
        Time opensWeekends = Time.valueOf("10:00:00");
        Time closesWeekends = Time.valueOf("20:00:00");
        when(restaurantsEntity.getRestaurantId()).thenReturn(restaurantId);
        when(restaurantsEntity.getName()).thenReturn(name);
        when(restaurantsEntity.getOpensWeekdays()).thenReturn(opensWeekdays);
        when(restaurantsEntity.getClosesWeekdays()).thenReturn(closesWeekdays);
        when(restaurantsEntity.getOpensWeekends()).thenReturn(opensWeekends);
        when(restaurantsEntity.getClosesWeekends()).thenReturn(closesWeekends);
        RestaurantsDTO restaurantsDTO = RestaurantsDTO.fromEntity(restaurantsEntity);
        assertEquals(restaurantId, restaurantsDTO.getRestaurantId());
        assertEquals(name, restaurantsDTO.getName());
        assertEquals(opensWeekdays, restaurantsDTO.getOpensWeekdays());
        assertEquals(closesWeekdays, restaurantsDTO.getClosesWeekdays());
        assertEquals(opensWeekends, restaurantsDTO.getOpensWeekends());
        assertEquals(closesWeekends, restaurantsDTO.getClosesWeekends());
    }



}
