package pl.foodapp.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.accounts.AccountTypesDTO;
import pl.foodapp.database.AccountTypesEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountTypesDTOTest {
    private AccountTypesDTO accountTypesDTO;

    @Mock
    private AccountTypesEntity accountTypesEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        accountTypesDTO = new AccountTypesDTO();
    }

    @Test
    public void testSetAndGetTypeId() {
        int typeId = 1;
        accountTypesDTO.setTypeId(typeId);
        assertEquals(typeId, accountTypesDTO.getTypeId());
    }

    @Test
    public void testSetAndGetName() {
        String name = "test";
        accountTypesDTO.setName(name);
        assertEquals(name, accountTypesDTO.getName());
    }

    @Test
    public void testFromEntity() {
        int typeId = 1;
        String name = "test";
        when(accountTypesEntity.getTypeId()).thenReturn(typeId);
        when(accountTypesEntity.getName()).thenReturn(name);
        accountTypesDTO.fromEntity(accountTypesEntity);
        assertEquals(typeId, accountTypesDTO.getTypeId());
        assertEquals(name, accountTypesDTO.getName());
    }
}
