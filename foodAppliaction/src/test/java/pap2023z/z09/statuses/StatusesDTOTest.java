package pap2023z.z09.statuses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import pap2023z.z09.database.StatusesEntity;

public class StatusesDTOTest {
    private StatusesDTO statusDTO;
    public static int exampleInt = 1;
    public static String exampleString = "a";
    @Mock
    private StatusesEntity statusEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        statusDTO = new StatusesDTO();
    }

    @Test
    public void testSetAndGetStatusId() {

        statusDTO.setStatusId(exampleInt);
        assertEquals(exampleInt, statusDTO.getStatusId());
    }

    @Test
    public void testSetAndGetName() {

        statusDTO.setName(exampleString);
        assertEquals(exampleString, statusDTO.getName());
    }

    @Test
    public void testDTOFromEntity() {

        when(statusEntity.getStatusId()).thenReturn(exampleInt);
        when(statusEntity.getName()).thenReturn(exampleString);

        statusDTO = new StatusesDTO().fromEntity(statusEntity);

        assertEquals(exampleInt, statusDTO.getStatusId());
        assertEquals(exampleString, statusDTO.getName());

    }
}