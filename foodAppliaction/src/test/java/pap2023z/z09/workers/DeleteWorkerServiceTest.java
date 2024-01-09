package pap2023z.z09.workers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
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
