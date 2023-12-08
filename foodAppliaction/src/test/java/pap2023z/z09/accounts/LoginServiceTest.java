package pap2023z.z09.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.AccountsEntity;

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
}
