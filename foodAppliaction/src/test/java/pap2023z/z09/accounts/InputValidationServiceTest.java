package pap2023z.z09.accounts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputValidationServiceTest {
    private InputValidationService inputValidationService = new InputValidationService();

    @Test
    public void validateEmailSuccessTest() {
        inputValidationService.validateEmail("email@test.com");
    }

    @Test
    public void validateEmailThrowsExceptionEmptyTest() {
        assertThrows(IllegalArgumentException.class, () -> inputValidationService.validateEmail(""));
    }

    @Test
    public void validateEmailThrowsExceptionNullTest() {
        assertThrows(IllegalArgumentException.class, () -> inputValidationService.validateEmail(null));
    }

    @Test
    public void validateEmailThrowsExceptionInvalidTest() {
        assertThrows(IllegalArgumentException.class, () -> inputValidationService.validateEmail("email"));
    }

    @Test
    public void validatePasswordSuccessTest() {
        inputValidationService.validatePassword("password");
    }

    @Test
    public void validatePasswordThrowsExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> inputValidationService.validatePassword(""));
    }

    @Test
    public void validatePasswordThrowsExceptionNullTest() {
        assertThrows(IllegalArgumentException.class, () -> inputValidationService.validatePassword(null));
    }
}
