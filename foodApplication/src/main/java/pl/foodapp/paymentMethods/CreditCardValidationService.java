package pl.foodapp.paymentMethods;

import java.sql.Date;
import java.time.LocalDate;

public class CreditCardValidationService {
    public void validateCardNumber(String cardNumber) {
        // check if card number is 16 digits long
        if (cardNumber == null || cardNumber.length() != 16) {
            throw new IllegalArgumentException("Card number must be 16 digits long");
        }
    }

    public void validateCvv(String cvv) {
        // check if CVV is 3 digits long
        if (cvv == null || cvv.length() != 3) {
            throw new IllegalArgumentException("CVV must be 3 digits long");
        }
    }

    public void validateExpiryDate(Date expiryDate) throws ExpiredCardException {
        // check if card is not expired
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date cannot be null");
        }
        // java.sql.Date adds 1900 to the year, that's why we need to convert it from LocalDate
        LocalDate today = LocalDate.now();
        LocalDate expiry = expiryDate.toLocalDate();
        int year = expiry.getYear();
        int month = expiry.getMonthValue();
        if (year < today.getYear() || (year == today.getYear() && month < today.getMonthValue())) {
            throw new ExpiredCardException();
        }
    }
}
