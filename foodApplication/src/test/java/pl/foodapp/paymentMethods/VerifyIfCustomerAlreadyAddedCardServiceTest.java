package pl.foodapp.paymentMethods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.database.PaymentMethodsEntity;
import pl.foodapp.paymentMethods.CardAlreadyAddedException;
import pl.foodapp.paymentMethods.PaymentMethodsDAO;
import pl.foodapp.paymentMethods.PaymentMethodsDTO;
import pl.foodapp.paymentMethods.VerifyIfCustomerAlreadyAddedCardService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class VerifyIfCustomerAlreadyAddedCardServiceTest {
    @InjectMocks
    private VerifyIfCustomerAlreadyAddedCardService verifyIfCustomerAlreadyAddedCardService;

    @Mock
    private PaymentMethodsDAO paymentMethodsDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void verifyIfCustomerAlreadyAddedCardSuccessTest() throws CardAlreadyAddedException {
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of());
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "1234567890123456", Date.valueOf(localDate), "123", 1);
        verifyIfCustomerAlreadyAddedCardService.verifyIfCustomerAlreadyAddedCard(dto);
    }

    @Test
    public void verifyIfCustomerAlreadyAddedCardThrowsExceptionTest() throws CardAlreadyAddedException {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        PaymentMethodsDTO dto = new PaymentMethodsDTO(1, "1234567890123456", Date.valueOf(localDate), "123", 1);
        PaymentMethodsEntity paymentMethodsEntity = new PaymentMethodsEntity();
        paymentMethodsEntity.setCardNumber("1234567890123456");
        paymentMethodsEntity.setCustomer(1);
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of(paymentMethodsEntity));
        assertThrows(CardAlreadyAddedException.class, () -> verifyIfCustomerAlreadyAddedCardService.verifyIfCustomerAlreadyAddedCard(dto));
    }

}
