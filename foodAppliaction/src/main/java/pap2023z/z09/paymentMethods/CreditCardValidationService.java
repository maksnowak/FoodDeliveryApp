package pap2023z.z09.paymentMethods;

import java.sql.Date;
import java.time.LocalDate;

public class CreditCardValidationService {
    public void validateCardNumber(String cardNumber) {
        // sprawdzenie czy numer karty jest 16-cyfrowy
        if (cardNumber == null || cardNumber.length() != 16) {
            throw new IllegalArgumentException("Card number must be 16 digits long");
        }
    }

    public void validateCvv(String cvv) {
        // sprawdzenie czy kod CVV jest 3-cyfrowy
        if (cvv == null || cvv.length() != 3) {
            throw new IllegalArgumentException("CVV must be 3 digits long");
        }
    }

    public void validateExpiryDate(Date expiryDate) throws ExpiredCardException {
        // sprawdzenie czy karta jest ważna
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date cannot be null");
        }
        // java.sql.Date dodaje 1900 do roku, dlatego potrzebna jest konwersja z LocalDate
        LocalDate today = LocalDate.now();
        LocalDate expiry = expiryDate.toLocalDate();
        int year = expiry.getYear();
        int month = expiry.getMonthValue();
        if (year < today.getYear() || (year == today.getYear() && month < today.getMonthValue())) {
            throw new ExpiredCardException();
        }
    }
}
