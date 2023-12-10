package pap2023z.z09.accounts;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.AccountsEntity;

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
    private AccountsEntity account;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void verifyEmailDoesNotThrowExecptionTest() throws EmailAlreadyExistsException {
        when(accountsDAO.getAccountByEmail("email")).thenReturn(null);
        signUpService.verifyEmail("email");
    }

    @Test
    public void verifyEmailThrowsExceptionTest() {
        when(accountsDAO.getAccountByEmail("email")).thenReturn(account);
        assertThrows(EmailAlreadyExistsException.class, () -> signUpService.verifyEmail("email"));
    }

    @Test
    public void signUpSuccessTest() throws EmailAlreadyExistsException {
        AccountsDTO dto = new AccountsDTO(1, "email", "password", 1, "name", "surname");
        doReturn(null).when(accountsDAO).getAccountByEmail("email");
        doNothing().when(accountsDAO).addAccount(any());
        signUpService.signUp(dto);
        verify(accountsDAO, times(1)).addAccount(any());
    }

    @Test
    public void signUpFailEmailExistsTest() throws EmailAlreadyExistsException {
        AccountsDTO dto = new AccountsDTO(1, "email", "password", 1, "name", "surname");
        when(accountsDAO.getAccountByEmail("email")).thenReturn(account);
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
    public void signUpFailPasswordNullTest() throws EmailAlreadyExistsException {
        AccountsDTO dto = new AccountsDTO(1, "email", null, 1, "name", "surname");
        doThrow(IllegalArgumentException.class).when(inputValidationService).validatePassword(null);
        assertThrows(IllegalArgumentException.class, () -> signUpService.signUp(dto));
        verify(accountsDAO, times(0)).addAccount(any());
    }

    @Test
    public void signUpFailPasswordEmptyTest() throws EmailAlreadyExistsException {
        AccountsDTO dto = new AccountsDTO(1, "email", "", 1, "name", "surname");
        doThrow(IllegalArgumentException.class).when(inputValidationService).validatePassword("");
        assertThrows(IllegalArgumentException.class, () -> signUpService.signUp(dto));
        verify(accountsDAO, times(0)).addAccount(any());
    }
}
