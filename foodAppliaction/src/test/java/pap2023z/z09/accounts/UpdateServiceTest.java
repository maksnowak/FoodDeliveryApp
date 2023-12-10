package pap2023z.z09.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.AccountsEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateServiceTest {
    @InjectMocks
    private UpdateService updateService;

    @Mock
    private AccountsDAO accountsDAO;

    @Mock
    private InputValidationService inputValidationService;

    @Mock
    private VerifyIfEmailAlreadyExistsService verifyIfEmailAlreadyExistsService;

    @Mock
    private AccountsEntity account;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void updateSuccessTest() throws EmailAlreadyExistsException {
        AccountsDTO dto = new AccountsDTO(1, "email", "password", 1, "name", "surname");
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        doNothing().when(accountsDAO).updateAccount(any());
        updateService.updateAccount(dto);
        verify(accountsDAO, times(1)).updateAccount(any());
    }

    @Test
    public void updateFailAccountDoesNotExistTest() {
        AccountsDTO dto = new AccountsDTO(1, "email", "password", 1, "name", "surname");
        when(accountsDAO.getAccountById(1)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> updateService.updateAccount(dto));
        verify(accountsDAO, times(0)).updateAccount(any());
    }

    @Test
    public void updateFailEmailExistsTest() throws EmailAlreadyExistsException {
        AccountsDTO dto = new AccountsDTO(1, "email", "password", 1, "name", "surname");
        AccountsEntity existingAccount = new AccountsEntity();
        existingAccount.setEmail("different_email");
        when(accountsDAO.getAccountById(1)).thenReturn(existingAccount);
        doThrow(EmailAlreadyExistsException.class).when(verifyIfEmailAlreadyExistsService).verifyEmail("email");
        assertThrows(EmailAlreadyExistsException.class, () -> updateService.updateAccount(dto));
        verify(accountsDAO, times(0)).updateAccount(any());
    }

    @Test
    public void updateFailEmailNullTest() {
        AccountsDTO dto = new AccountsDTO(1, null, "password", 1, "name", "surname");
        doThrow(IllegalArgumentException.class).when(inputValidationService).validateEmail(null);
        assertThrows(IllegalArgumentException.class, () -> updateService.updateAccount(dto));
        verify(accountsDAO, times(0)).updateAccount(any());
    }

    @Test
    public void updateFailPasswordNullTest() {
        AccountsDTO dto = new AccountsDTO(1, "email", null, 1, "name", "surname");
        doThrow(IllegalArgumentException.class).when(inputValidationService).validatePassword(null);
        assertThrows(IllegalArgumentException.class, () -> updateService.updateAccount(dto));
        verify(accountsDAO, times(0)).updateAccount(any());
    }

    @Test
    public void updateFailEmailEmptyTest() {
        AccountsDTO dto = new AccountsDTO(1, "", "password", 1, "name", "surname");
        doThrow(IllegalArgumentException.class).when(inputValidationService).validateEmail("");
        assertThrows(IllegalArgumentException.class, () -> updateService.updateAccount(dto));
        verify(accountsDAO, times(0)).updateAccount(any());
    }

    @Test
    public void updateFailPasswordEmptyTest() {
        AccountsDTO dto = new AccountsDTO(1, "email", "", 1, "name", "surname");
        doThrow(IllegalArgumentException.class).when(inputValidationService).validatePassword("");
        assertThrows(IllegalArgumentException.class, () -> updateService.updateAccount(dto));
        verify(accountsDAO, times(0)).updateAccount(any());
    }
}
