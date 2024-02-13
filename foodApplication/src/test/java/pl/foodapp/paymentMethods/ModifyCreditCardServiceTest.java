package pl.foodapp.paymentMethods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.database.PaymentMethodsEntity;
import pl.foodapp.paymentMethods.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

public class ModifyCreditCardServiceTest {
    @InjectMocks
    private ModifyCreditCardService modifyCreditCardService;

    @Mock
    private PaymentMethodsDAO paymentMethodsDAO;

    @Mock
    private VerifyIfCustomerAlreadyAddedCardService verifyIfCustomerAlreadyAddedCardService;

    @Mock
    private CreditCardValidationService creditCardValidationService;

    @Mock
    private PaymentMethodsEntity paymentMethodsEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void modifyCardSuccessTest() throws ExpiredCardException, CardAlreadyAddedException {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        when(paymentMethodsDAO.getMethodIdById(1)).thenReturn(paymentMethodsEntity);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "1234567890123456", Date.valueOf(localDate), "123", 1);
        doNothing().when(paymentMethodsDAO).updateMethod(any());
        modifyCreditCardService.modifyCreditCard(dto);
        verify(paymentMethodsDAO, times(1)).updateMethod(any());
    }

    @Test
    public void modifyCardFailExpiredTest() throws ExpiredCardException {
        LocalDate localDate = LocalDate.of(2020, 12, 31);
        when(paymentMethodsDAO.getMethodIdById(1)).thenReturn(paymentMethodsEntity);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "1234567890123456", Date.valueOf(localDate), "123", 1);
        doThrow(ExpiredCardException.class).when(creditCardValidationService).validateExpiryDate(Date.valueOf(localDate));
        assertThrows(ExpiredCardException.class, () -> modifyCreditCardService.modifyCreditCard(dto));
    }

    @Test
    public void modifyCardFailAlreadyAddedTest() throws ExpiredCardException, CardAlreadyAddedException {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        when(paymentMethodsDAO.getMethodIdById(1)).thenReturn(paymentMethodsEntity);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "1234567890123456", Date.valueOf(localDate), "123", 1);
        doThrow(CardAlreadyAddedException.class).when(verifyIfCustomerAlreadyAddedCardService).verifyIfCustomerAlreadyAddedCard(dto);
        assertThrows(CardAlreadyAddedException.class, () -> modifyCreditCardService.modifyCreditCard(dto));
    }

    @Test
    public void modifyCardFailNoMethodTest() throws ExpiredCardException, CardAlreadyAddedException {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "1234567890123456", Date.valueOf(localDate), "123", 1);
        when(paymentMethodsDAO.getMethodIdById(1)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> modifyCreditCardService.modifyCreditCard(dto));
    }

    @Test
    public void modifyCardFailCardNumberTest() throws ExpiredCardException {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "123456789012345", Date.valueOf(localDate), "123", 1);
        doThrow(IllegalArgumentException.class).when(creditCardValidationService).validateCardNumber("123456789012345");
        assertThrows(IllegalArgumentException.class, () -> modifyCreditCardService.modifyCreditCard(dto));
    }

    @Test
    public void modifyCardFailCvvTest() throws ExpiredCardException {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "1234567890123456", Date.valueOf(localDate), "12", 1);
        doThrow(IllegalArgumentException.class).when(creditCardValidationService).validateCvv("12");
        assertThrows(IllegalArgumentException.class, () -> modifyCreditCardService.modifyCreditCard(dto));
    }

}
