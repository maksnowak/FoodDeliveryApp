package pap2023z.z09.paymentMethods;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

public class CreditCardValidationServiceTest {
    private CreditCardValidationService creditCardValidationService = new CreditCardValidationService();

    @Test
    public void testValidateCardNumberSuccess() {
        assertDoesNotThrow(() -> creditCardValidationService.validateCardNumber("1234567890123456"));
    }

    @Test
    public void testValidateCardNumberThrowsExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> creditCardValidationService.validateCardNumber(""));
        assertThrows(IllegalArgumentException.class, () -> creditCardValidationService.validateCardNumber("12345678901234567"));
        assertThrows(IllegalArgumentException.class, () -> creditCardValidationService.validateCardNumber("123456789012345"));
    }

    @Test
    public void testValidateCardNumberThrowsExceptionNullTest() {
        assertThrows(IllegalArgumentException.class, () -> creditCardValidationService.validateCardNumber(null));
    }

    @Test
    public void testValidateCvvSuccess() {
        assertDoesNotThrow(() -> creditCardValidationService.validateCvv("123"));
        assertDoesNotThrow(() -> creditCardValidationService.validateCvv("000"));
    }

    @Test
    public void testValidateCvvThrowsExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> creditCardValidationService.validateCvv(""));
        assertThrows(IllegalArgumentException.class, () -> creditCardValidationService.validateCvv("1234"));
        assertThrows(IllegalArgumentException.class, () -> creditCardValidationService.validateCvv("12"));
    }

    @Test
    public void testValidateCvvThrowsExceptionNullTest() {
        assertThrows(IllegalArgumentException.class, () -> creditCardValidationService.validateCvv(null));
    }

    @Test
    public void testValidateExpiryDateSuccess() {
        LocalDate localDate = LocalDate.of(2024, 12, 31);
        assertDoesNotThrow(() -> creditCardValidationService.validateExpiryDate(Date.valueOf(localDate)));
    }

    @Test
    public void testValidateExpiryDateThrowsExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> creditCardValidationService.validateExpiryDate(null));
    }

    @Test
    public void testValidateExpiryDateThrowsExceptionExpiredTest() {
        LocalDate localDate = LocalDate.of(2020, 12, 31);
        assertThrows(ExpiredCardException.class, () -> creditCardValidationService.validateExpiryDate(Date.valueOf(localDate)));
    }
}
