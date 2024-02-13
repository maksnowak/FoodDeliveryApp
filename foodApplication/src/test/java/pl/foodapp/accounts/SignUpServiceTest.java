package pl.foodapp.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.accounts.*;
import pl.foodapp.database.AccountsEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SignUpServiceTest {
    @InjectMocks
    private SignUpService signUpService;

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
    public void signUpSuccessTest() throws EmailAlreadyExistsException {
        AccountsDTO dto = new AccountsDTO(1, "email@test.com", "password", 1, "name", "surname");
        doReturn(null).when(accountsDAO).getAccountByEmail("email@test.com");
        doNothing().when(accountsDAO).addAccount(any());
        signUpService.signUp(dto);
        verify(accountsDAO, times(1)).addAccount(any());
    }

    @Test
    public void signUpFailEmailExistsTest() throws EmailAlreadyExistsException {
        AccountsDTO dto = new AccountsDTO(1, "email@test.com", "password", 1, "name", "surname");
        when(accountsDAO.getAccountByEmail("email@test.com")).thenReturn(account);
        doThrow(EmailAlreadyExistsException.class).when(verifyIfEmailAlreadyExistsService).verifyEmail("email@test.com");
        assertThrows(EmailAlreadyExistsException.class, () -> signUpService.signUp(dto));
        verify(accountsDAO, times(0)).addAccount(any());
    }

    @Test
    public void signUpFailEmailNullTest() throws EmailAlreadyExistsException {
        AccountsDTO dto = new AccountsDTO(1, null, "password", 1, "name", "surname");
        doThrow(IllegalArgumentException.class).when(inputValidationService).validateEmail(null);
        assertThrows(IllegalArgumentException.class, () -> signUpService.signUp(dto));
        verify(accountsDAO, times(0)).addAccount(any());
    }

    @Test
    public void signUpFailEmailEmptyTest() throws EmailAlreadyExistsException {
        AccountsDTO dto = new AccountsDTO(1, "", "password", 1, "name", "surname");
        doThrow(IllegalArgumentException.class).when(inputValidationService).validateEmail("");
        assertThrows(IllegalArgumentException.class, () -> signUpService.signUp(dto));
        verify(accountsDAO, times(0)).addAccount(any());
    }

    @Test
    public void signUpFailNotValidEmailTest() throws EmailAlreadyExistsException {
        AccountsDTO dto = new AccountsDTO(1, "email", "password", 1, "name", "surname");
        doThrow(IllegalArgumentException.class).when(inputValidationService).validateEmail("email");
        assertThrows(IllegalArgumentException.class, () -> signUpService.signUp(dto));
        verify(accountsDAO, times(0)).addAccount(any());
    }

    @Test
    public void signUpFailPasswordNullTest() throws EmailAlreadyExistsException {
        AccountsDTO dto = new AccountsDTO(1, "email@test.com", null, 1, "name", "surname");
        doThrow(IllegalArgumentException.class).when(inputValidationService).validatePassword(null);
        assertThrows(IllegalArgumentException.class, () -> signUpService.signUp(dto));
        verify(accountsDAO, times(0)).addAccount(any());
    }

    @Test
    public void signUpFailPasswordEmptyTest() throws EmailAlreadyExistsException {
        AccountsDTO dto = new AccountsDTO(1, "email@test.com", "", 1, "name", "surname");
        doThrow(IllegalArgumentException.class).when(inputValidationService).validatePassword("");
        assertThrows(IllegalArgumentException.class, () -> signUpService.signUp(dto));
        verify(accountsDAO, times(0)).addAccount(any());
    }
}
