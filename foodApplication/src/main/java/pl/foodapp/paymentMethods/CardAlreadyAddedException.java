package pl.foodapp.paymentMethods;

public class CardAlreadyAddedException  extends Exception {
    public CardAlreadyAddedException() {
        super("Card already added by this customer");
    }

    public CardAlreadyAddedException(String message) {
        super(message);
    }
}
