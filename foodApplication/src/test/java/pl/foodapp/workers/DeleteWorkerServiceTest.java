package pl.foodapp.workers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.workers.DeleteWorkerService;
import pl.foodapp.workers.WorkersDAO;

import static org.mockito.Mockito.*;

public class DeleteWorkerServiceTest {
    @InjectMocks
    private DeleteWorkerService service;

    @Mock
    private WorkersDAO workersDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteWorker() {
        int id = 1;
        doNothing().when(workersDAO).deleteWorker(id);
        workersDAO.deleteWorker(id);
        verify(workersDAO, times(1)).deleteWorker(id);
    }
}
