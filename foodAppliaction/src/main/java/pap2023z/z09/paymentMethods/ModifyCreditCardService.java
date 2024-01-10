package pap2023z.z09.paymentMethods;

import pap2023z.z09.database.PaymentMethodsEntity;

public class ModifyCreditCardService {
    private final PaymentMethodsDAO paymentMethodsDAO;
    private final CreditCardValidationService creditCardValidationService;
    private final VerifyIfCustomerAlreadyAddedCardService verifyIfCustomerAlreadyAddedCardService;

    public ModifyCreditCardService(PaymentMethodsDAO paymentMethodsDAO, CreditCardValidationService creditCardValidationService, VerifyIfCustomerAlreadyAddedCardService verifyIfCustomerAlreadyAddedCardService) {
        this.paymentMethodsDAO = paymentMethodsDAO;
        this.creditCardValidationService = creditCardValidationService;
        this.verifyIfCustomerAlreadyAddedCardService = verifyIfCustomerAlreadyAddedCardService;
    }

    public void modifyCreditCard(PaymentMethodsDTO methodDTO) throws ExpiredCardException, CardAlreadyAddedException {
        PaymentMethodsEntity oldEntity = paymentMethodsDAO.getMethodIdById(methodDTO.getMethodId());
        if (oldEntity == null) {
            throw new IllegalArgumentException("No such method");
        }
        // sprawdź czy dane karty są poprawne i karta nie wygasła
        creditCardValidationService.validateCardNumber(methodDTO.getCardNumber());
        creditCardValidationService.validateCvv(methodDTO.getCvv());
        creditCardValidationService.validateExpiryDate(methodDTO.getExpiryDate());
        verifyIfCustomerAlreadyAddedCardService.verifyIfCustomerAlreadyAddedCard(methodDTO); // sprawdzanie czy użytkownik nie dodał już karty o podanym numerze
        PaymentMethodsEntity entity = new PaymentMethodsEntity();
        entity.setMethodId(methodDTO.getMethodId());
        entity.setCardNumber(methodDTO.getCardNumber());
        entity.setCvv(methodDTO.getCvv());
        entity.setExpiryDate(methodDTO.getExpiryDate());
        entity.setCustomer(methodDTO.getCustomer());
        paymentMethodsDAO.updateMethod(entity);
    }
}
