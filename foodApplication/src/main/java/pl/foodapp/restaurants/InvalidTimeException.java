package pl.foodapp.restaurants;

public class InvalidTimeException extends Exception{
    public InvalidTimeException() {
        super("Invalid time");
    }

    public InvalidTimeException(String message) {
        super(message);
    }
}
