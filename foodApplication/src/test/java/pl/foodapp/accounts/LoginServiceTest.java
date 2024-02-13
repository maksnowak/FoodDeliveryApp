package pl.foodapp.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.accounts.AccountsDAO;
import pl.foodapp.accounts.AccountsDTO;
import pl.foodapp.accounts.LoginService;
import pl.foodapp.database.AccountsEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {
    @InjectMocks
    private LoginService loginService;

    @Mock
    private AccountsDAO accountsDAO;

    @Mock
    private AccountsEntity account;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginSuccess() {
        when(accountsDAO.getAccountByEmail("admin@example.com")).thenReturn(account);
        when(account.getPassword()).thenReturn("admin");
        assertTrue(loginService.login("admin@example.com", "admin"));
    }

    @Test
    public void testLoginFail() {
        when(accountsDAO.getAccountByEmail("admin@example.com")).thenReturn(account);
        when(account.getPassword()).thenReturn("admin");
        assertFalse(loginService.login("admin@example.com", "wrong"));
    }

    @Test
    public void testLoginNoAccount() {
        when(accountsDAO.getAccountByEmail("no_account@example.com")).thenReturn(null);
        assertFalse(loginService.login("no_account@example.com", "wrong"));
    }

    @Test
    public void testConvertToDTO() {
        when(account.getAccountId()).thenReturn(1);
        when(account.getEmail()).thenReturn("email");
        when(account.getPassword()).thenReturn("password");
        when(account.getType()).thenReturn(1);
        when(account.getName()).thenReturn("name");
        when(account.getSurname()).thenReturn("surname");
        AccountsDTO dto = loginService.convertToDTO(account);
        assertEquals(1, dto.getAccountId());
        assertEquals("email", dto.getEmail());
        assertEquals("password", dto.getPassword());
        assertEquals(1, dto.getType());
        assertEquals("name", dto.getName());
        assertEquals("surname", dto.getSurname());
    }
}
