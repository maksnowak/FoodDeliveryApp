package pl.foodapp.workers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.database.WorkersEntity;
import pl.foodapp.restaurants.RestaurantsDAO;
import pl.foodapp.restaurants.RestaurantsDTO;
import pl.foodapp.workers.ViewWorkerRestaurantsService;
import pl.foodapp.workers.WorkersDAO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ViewWorkerRestaurantsServiceTest {
@InjectMocks
    private ViewWorkerRestaurantsService service;

    @Mock
    private WorkersDAO workersDAO;

    @Mock
    private RestaurantsDAO restaurantsDAO;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWorkerRestaurants() {
        int id = 1;
        WorkersEntity testWorker = new WorkersEntity();
        RestaurantsEntity testRestaurant = new RestaurantsEntity();
        testWorker.setId(id);
        testWorker.setWorker(id);
        testWorker.setRestaurantId(id);
        testRestaurant.setRestaurantId(id);
        testRestaurant.setName("test");
        when(workersDAO.getWorkersByAccountId(id)).thenReturn(List.of(testWorker));
        when(restaurantsDAO.getRestaurantById(id)).thenReturn(testRestaurant);
        List<RestaurantsDTO> restaurants = service.getWorkerRestaurants(id);
        assertEquals(1, restaurants.size());
        assertEquals("test", restaurants.getFirst().getName());
    }

    @Test
    public void testGetWorkerRestaurantsEmpty() {
        int id = 1;
        when(workersDAO.getWorkersByAccountId(id)).thenReturn(List.of());
        List<RestaurantsDTO> restaurants = service.getWorkerRestaurants(id);
        assertEquals(0, restaurants.size());
    }
}
