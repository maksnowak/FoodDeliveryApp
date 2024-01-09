package pap2023z.z09.accounts;

public class InputValidationService {
    public void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$"; // wyrażenie regularne do sprawdzania poprawności adresu email
        if (!email.matches(regex)) {
            throw new IllegalArgumentException("Not a valid email address");
        }
    }

    public void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }
}
