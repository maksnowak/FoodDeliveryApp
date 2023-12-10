package pap2023z.z09.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.AccountsEntity;

import static org.mockito.Mockito.*;

public class DeleteServiceTest {
    @InjectMocks
    private DeleteService deleteService;

    @Mock
    private AccountsDAO accountsDAO;

    @Mock
    private AccountsEntity account;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteAccount() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteNoAccount() {
        when(accountsDAO.getAccountById(1)).thenReturn(null);
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(0)).deleteAccount(account);
    }
}
