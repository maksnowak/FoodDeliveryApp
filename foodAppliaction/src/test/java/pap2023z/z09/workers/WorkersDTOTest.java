package pap2023z.z09.workers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import pap2023z.z09.database.WorkersEntity;

public class WorkersDTOTest {
    private WorkersDTO workerDTO;

    @Mock
    private WorkersEntity workerEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        workerDTO = new WorkersDTO();
    }

    @Test
    public void testSetAndGetId() {
        int exampleInt = 1;
        workerDTO.setId(exampleInt);
        assertEquals(exampleInt, workerDTO.getId());
    }

    @Test
    public void testSetAndGetWorker() {
        int exampleInt = 1;
        workerDTO.setWorker(exampleInt);
        assertEquals(exampleInt, workerDTO.getWorker());
    }

    @Test
    public void testSetAndGetRestaurantId() {
        int exampleInt = 1;
        workerDTO.setRestaurantId(exampleInt);
        assertEquals(exampleInt, workerDTO.getRestaurantId());
    }

    @Test
    public void testDTOFromEntity() {
        int exampleInt = 1;
        when(workerEntity.getId()).thenReturn(exampleInt);
        when(workerEntity.getWorker()).thenReturn(exampleInt);
        when(workerEntity.getRestaurantId()).thenReturn(exampleInt);

        workerDTO = new WorkersDTO().fromEntity(workerEntity);

        assertEquals(exampleInt, workerDTO.getId());
        assertEquals(exampleInt, workerDTO.getWorker());
        assertEquals(exampleInt, workerDTO.getRestaurantId());
    }
}
