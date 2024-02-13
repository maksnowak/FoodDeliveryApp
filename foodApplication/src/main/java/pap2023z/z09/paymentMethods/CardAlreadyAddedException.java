package pap2023z.z09.paymentMethods;

public class CardAlreadyAddedException  extends Exception {
    public CardAlreadyAddedException() {
        super("Card already added by this customer");
    }

    public CardAlreadyAddedException(String message) {
        super(message);
    }
}
