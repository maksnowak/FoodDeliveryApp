package pap2023z.z09.paymentMethods;

import pap2023z.z09.database.PaymentMethodsEntity;

public class VerifyIfCustomerAlreadyAddedCardService {
    private final PaymentMethodsDAO paymentMethodsDAO;

    public VerifyIfCustomerAlreadyAddedCardService(PaymentMethodsDAO paymentMethodsDAO) {
        this.paymentMethodsDAO = paymentMethodsDAO;
    }

    public void verifyIfCustomerAlreadyAddedCard(PaymentMethodsDTO DTO) throws CardAlreadyAddedException {
        // sprawdzenie czy dany użytkownik dodał już kartę o podanym numerze
        for (PaymentMethodsEntity method : paymentMethodsDAO.getMethodsByCustomerId(DTO.getCustomer())) {
            if (method.getCardNumber().equals(DTO.getCardNumber())) {
                throw new CardAlreadyAddedException();
            }
        }
    }
}
