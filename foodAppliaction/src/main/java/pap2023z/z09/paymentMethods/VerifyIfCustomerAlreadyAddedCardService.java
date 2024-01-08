package pap2023z.z09.paymentMethods;

import pap2023z.z09.database.PaymentMethodsEntity;

public class VerifyIfCustomerAlreadyAddedCardService {
    private final PaymentMethodsDAO paymentMethodsDAO;

    public VerifyIfCustomerAlreadyAddedCardService(PaymentMethodsDAO paymentMethodsDAO) {
        this.paymentMethodsDAO = paymentMethodsDAO;
    }

    public void verifyIfCustomerAlreadyAddedCard(PaymentMethodsDTO DTO) throws CardAlreadyAddedException {
        for (PaymentMethodsEntity method : paymentMethodsDAO.getAllMethods()) {
            if (method.getCardNumber().equals(DTO.getCardNumber()) && method.getCustomer() == DTO.getCustomer()) {
                throw new CardAlreadyAddedException();
            }
        }
    }
}
