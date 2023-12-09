package pap2023z.z09.accounts;

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException() {
        super("Email already exists");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
