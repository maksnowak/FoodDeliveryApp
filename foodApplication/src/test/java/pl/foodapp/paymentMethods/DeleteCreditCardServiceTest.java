package pl.foodapp.paymentMethods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.database.PaymentMethodsEntity;
import pl.foodapp.paymentMethods.DeleteCreditCardService;
import pl.foodapp.paymentMethods.PaymentMethodsDAO;

import static org.mockito.Mockito.*;

public class DeleteCreditCardServiceTest {
    @InjectMocks
    private DeleteCreditCardService deleteCreditCardService;

    @Mock
    private PaymentMethodsDAO paymentMethodsDAO;

    @Mock
    private PaymentMethodsEntity method;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteCreditCard() {
        when(paymentMethodsDAO.getMethodIdById(1)).thenReturn(method);
        deleteCreditCardService.deleteCreditCard(1);
        verify(paymentMethodsDAO, times(1)).deleteMethod(method);
    }

    @Test
    public void testDeleteNoCreditCard() {
        when(paymentMethodsDAO.getMethodIdById(1)).thenReturn(null);
        deleteCreditCardService.deleteCreditCard(1);
        verify(paymentMethodsDAO, times(0)).deleteMethod(method);
    }
}
