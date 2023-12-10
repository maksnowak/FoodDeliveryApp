package pap2023z.z09.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.AccountsEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class VerifyIfEmailAlreadyExistsServiceTest {
    @InjectMocks
    private VerifyIfEmailAlreadyExistsService verifyIfEmailAlreadyExistsService;

        @Mock
        private AccountsDAO accountsDAO;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void verifyEmailSuccessTest() throws EmailAlreadyExistsException {
            when(accountsDAO.getAccountByEmail("email")).thenReturn(null);
            verifyIfEmailAlreadyExistsService.verifyEmail("email");
        }

        @Test
        public void verifyEmailThrowsExceptionTest() throws EmailAlreadyExistsException {
            when(accountsDAO.getAccountByEmail("email")).thenReturn(new AccountsEntity());
            assertThrows(EmailAlreadyExistsException.class, () -> verifyIfEmailAlreadyExistsService.verifyEmail("email"));
        }
}
