package pap2023z.z09.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.AccountsEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountsDTOTest {
    private AccountsDTO accountsDTO;

    @Mock
    private AccountsEntity accountsEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        accountsDTO = new AccountsDTO();
    }

    @Test
    public void testSetAndGetEmail() {
        String email = "test@example.com";
        accountsDTO.setEmail(email);
        assertEquals(email, accountsDTO.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        String password = "test";
        accountsDTO.setPassword(password);
        assertEquals(password, accountsDTO.getPassword());
    }

    @Test
    public void testSetAndGetAccountId() {
        int accountId = 1;
        accountsDTO.setAccountId(accountId);
        assertEquals(accountId, accountsDTO.getAccountId());
    }

    @Test
    public void testSetAndGetType() {
        int type = 1;
        accountsDTO.setType(type);
        assertEquals(type, accountsDTO.getType());
    }

    @Test
    public void testSetAndGetSurname() {
        String surname = "test";
        accountsDTO.setSurname(surname);
        assertEquals(surname, accountsDTO.getSurname());
    }

    @Test
    public void testSetAndGetName() {
        String name = "test";
        accountsDTO.setName(name);
        assertEquals(name, accountsDTO.getName());
    }

    @Test
    public void testFromEntity() {
        when(accountsEntity.getAccountId()).thenReturn(1);
        when(accountsEntity.getEmail()).thenReturn("test@example.com");
        when(accountsEntity.getPassword()).thenReturn("test");
        when(accountsEntity.getType()).thenReturn(1);
        when(accountsEntity.getName()).thenReturn("Test");
        when(accountsEntity.getSurname()).thenReturn("Testowy");

        AccountsDTO dto = AccountsDTO.fromEntity(accountsEntity);

        assertEquals(1, dto.getAccountId());
        assertEquals("test@example.com", dto.getEmail());
        assertEquals("test", dto.getPassword());
        assertEquals(1, dto.getType());
        assertEquals("Test", dto.getName());
        assertEquals("Testowy", dto.getSurname());
    }
}
