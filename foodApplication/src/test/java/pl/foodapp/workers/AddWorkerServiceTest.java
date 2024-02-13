package pl.foodapp.workers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.workers.AddWorkerService;
import pl.foodapp.workers.WorkersDAO;
import pl.foodapp.workers.WorkersDTO;

import static org.mockito.Mockito.*;

public class AddWorkerServiceTest {
    @InjectMocks
    private AddWorkerService service;

    @Mock
    private WorkersDAO workersDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddWorker() {
        WorkersDTO worker = new WorkersDTO();
        worker.setWorker(1);
        worker.setRestaurantId(1);
        doNothing().when(workersDAO).addWorker(worker.getWorker(), worker.getRestaurantId());
        workersDAO.addWorker(worker.getWorker(), worker.getRestaurantId());
        verify(workersDAO, times(1)).addWorker(worker.getWorker(), worker.getRestaurantId());
    }
}
