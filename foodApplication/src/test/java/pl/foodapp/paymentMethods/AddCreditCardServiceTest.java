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

public class AddCreditCardServiceTest {
    @InjectMocks
    private AddCreditCardService addCreditCardService;

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
    public void addCardSuccessTest() throws ExpiredCardException, CardAlreadyAddedException {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "1234567890123456", Date.valueOf(localDate), "123", 1);
        doNothing().when(paymentMethodsDAO).addMethod(any());
        addCreditCardService.addCreditCard(dto);
        verify(paymentMethodsDAO, times(1)).addMethod(any());
    }

    @Test
    public void addCardFailAlreadyAddedTest() throws ExpiredCardException, CardAlreadyAddedException {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "1234567890123456", Date.valueOf(localDate), "123", 1);
        doThrow(CardAlreadyAddedException.class).when(verifyIfCustomerAlreadyAddedCardService).verifyIfCustomerAlreadyAddedCard(dto);
        assertThrows(CardAlreadyAddedException.class, () -> addCreditCardService.addCreditCard(dto));
        verify(paymentMethodsDAO, times(0)).addMethod(any());
    }

    @Test
    public void addCardFailExpiredTest() throws ExpiredCardException {
        LocalDate localDate = LocalDate.of(2020, 12, 31);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "1234567890123456", Date.valueOf(localDate), "123", 1);
        doThrow(ExpiredCardException.class).when(creditCardValidationService).validateExpiryDate(Date.valueOf(localDate));
        assertThrows(ExpiredCardException.class, () -> addCreditCardService.addCreditCard(dto));
        verify(paymentMethodsDAO, times(0)).addMethod(any());
    }

    @Test
    public void addCardFailCardNumberTest() throws ExpiredCardException {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "123456789012345", Date.valueOf(localDate), "123", 1);
        doThrow(IllegalArgumentException.class).when(creditCardValidationService).validateCardNumber("123456789012345");
        assertThrows(IllegalArgumentException.class, () -> addCreditCardService.addCreditCard(dto));
        verify(paymentMethodsDAO, times(0)).addMethod(any());
    }

    @Test
    public void addCardFailCvvTest() throws ExpiredCardException {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "1234567890123456", Date.valueOf(localDate), "12", 1);
        doThrow(IllegalArgumentException.class).when(creditCardValidationService).validateCvv("12");
        assertThrows(IllegalArgumentException.class, () -> addCreditCardService.addCreditCard(dto));
        verify(paymentMethodsDAO, times(0)).addMethod(any());
    }

    @Test
    public void addCardFailCardNumberNullTest() throws ExpiredCardException {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, null, Date.valueOf(localDate), "123", 1);
        doThrow(IllegalArgumentException.class).when(creditCardValidationService).validateCardNumber(null);
        assertThrows(IllegalArgumentException.class, () -> addCreditCardService.addCreditCard(dto));
        verify(paymentMethodsDAO, times(0)).addMethod(any());
    }

    @Test
    public void addCardFailCardNumberEmptyTest() throws ExpiredCardException {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "", Date.valueOf(localDate), "123", 1);
        doThrow(IllegalArgumentException.class).when(creditCardValidationService).validateCardNumber("");
        assertThrows(IllegalArgumentException.class, () -> addCreditCardService.addCreditCard(dto));
        verify(paymentMethodsDAO, times(0)).addMethod(any());
    }

    @Test
    public void addCardFailCvvNullTest() throws ExpiredCardException {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "1234567890123456", Date.valueOf(localDate), null, 1);
        doThrow(IllegalArgumentException.class).when(creditCardValidationService).validateCvv(null);
        assertThrows(IllegalArgumentException.class, () -> addCreditCardService.addCreditCard(dto));
        verify(paymentMethodsDAO, times(0)).addMethod(any());
    }
}
