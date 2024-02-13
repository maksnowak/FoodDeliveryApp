package pap2023z.z09.paymentMethods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.sql.Date;

import pap2023z.z09.database.PaymentMethodsEntity;

public class PaymentMethodsDTOTest {
    private PaymentMethodsDTO paymentMethodDTO;
    public int exampleInt = 1;
    public String exampleCvv = "123";
    public String exampleString = "1234567890123456";
    public Date exampleDate = new Date(2000, 9, 11);
    @Mock
    private PaymentMethodsEntity paymentMethodEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentMethodDTO = new PaymentMethodsDTO();
    }

    @Test
    public void testSetAndGetMethodId() {

        paymentMethodDTO.setMethodId(exampleInt);
        assertEquals(exampleInt, paymentMethodDTO.getMethodId());
    }

    @Test
    public void testSetAndGetCardNumber() {

        paymentMethodDTO.setCardNumber(exampleString);
        assertEquals(exampleString, paymentMethodDTO.getCardNumber());
    }

    @Test
    public void testSetAndGetExpiryDate() {

        paymentMethodDTO.setExpiryDate(exampleDate);
        assertEquals(exampleDate, paymentMethodDTO.getExpiryDate());
    }

    @Test
    public void testSetAndGetCvv() {

        paymentMethodDTO.setCvv(exampleCvv);
        assertEquals(exampleCvv, paymentMethodDTO.getCvv());
    }

    @Test
    public void testSetAndGetCustomer() {

        paymentMethodDTO.setCustomer(exampleInt);
        assertEquals(exampleInt, paymentMethodDTO.getCustomer());
    }

    @Test
    public void testDTOFromEntity() {

        when(paymentMethodEntity.getMethodId()).thenReturn(exampleInt);
        when(paymentMethodEntity.getCustomer()).thenReturn(exampleInt);
        when(paymentMethodEntity.getCvv()).thenReturn(exampleCvv);
        when(paymentMethodEntity.getExpiryDate()).thenReturn(exampleDate);

        paymentMethodDTO = new PaymentMethodsDTO().fromEntity(paymentMethodEntity);

        assertEquals(exampleInt, paymentMethodDTO.getMethodId());
        assertEquals(exampleInt, paymentMethodDTO.getCustomer());
        assertEquals(exampleCvv, paymentMethodDTO.getCvv());
        assertEquals(exampleDate, paymentMethodDTO.getExpiryDate());

    }
}