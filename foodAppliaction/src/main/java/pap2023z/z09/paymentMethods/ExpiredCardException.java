package pap2023z.z09.paymentMethods;

public class ExpiredCardException extends Exception {
    public ExpiredCardException() {
        super("Card is expired");
    }

    public ExpiredCardException(String message) {
        super(message);
    }
}
