package pl.foodapp.paymentMethods;

import pl.foodapp.database.PaymentMethodsEntity;

public class VerifyIfCustomerAlreadyAddedCardService {
    private final PaymentMethodsDAO paymentMethodsDAO;

    public VerifyIfCustomerAlreadyAddedCardService(PaymentMethodsDAO paymentMethodsDAO) {
        this.paymentMethodsDAO = paymentMethodsDAO;
    }

    public void verifyIfCustomerAlreadyAddedCard(PaymentMethodsDTO DTO) throws CardAlreadyAddedException {
        // check if the user has already added a card with the given number
        for (PaymentMethodsEntity method : paymentMethodsDAO.getMethodsByCustomerId(DTO.getCustomer())) {
            if (method.getCardNumber().equals(DTO.getCardNumber())) {
                throw new CardAlreadyAddedException();
            }
        }
    }
}
