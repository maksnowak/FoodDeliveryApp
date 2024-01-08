package pap2023z.z09.dishTypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.DishTypesEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DishTypesDTOTest {
    private DishTypesDTO dishTypesDTO;

    @Mock
    private DishTypesEntity dishTypesEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        dishTypesDTO = new DishTypesDTO();
    }

    @Test
    public void testSetAndGetTypeId() {
        int typeId = 1;
        dishTypesDTO.setTypeId(typeId);
        assertEquals(typeId, dishTypesDTO.getTypeId());
    }

    @Test
    public void testSetAndGetName() {
        String name = "test";
        dishTypesDTO.setName(name);
        assertEquals(name, dishTypesDTO.getName());
    }

    @Test
    public void testFromEntity() {
        int typeId = 1;
        String name = "test";
        when(dishTypesEntity.getTypeId()).thenReturn(typeId);
        when(dishTypesEntity.getName()).thenReturn(name);
        DishTypesDTO dishTypesDTO = DishTypesDTO.fromEntity(dishTypesEntity);
        assertEquals(typeId, dishTypesDTO.getTypeId());
        assertEquals(name, dishTypesDTO.getName());
    }
}
