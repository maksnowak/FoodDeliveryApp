package pap2023z.z09.restaurants;

public class InvalidTimeException extends Exception{
    public InvalidTimeException() {
        super("Invalid time");
    }

    public InvalidTimeException(String message) {
        super(message);
    }
}
