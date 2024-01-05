package pap2023z.z09.paymentMethods;

import pap2023z.z09.database.PaymentMethodsEntity;

public class AddCreditCardService {
    private final PaymentMethodsDAO paymentMethodsDAO;
    private final CreditCardValidationService creditCardValidationService;
    private final VerifyIfCustomerAlreadyAddedCardService verifyIfCustomerAlreadyAddedCardService;

    public AddCreditCardService(PaymentMethodsDAO paymentMethodsDAO, CreditCardValidationService creditCardValidationService, VerifyIfCustomerAlreadyAddedCardService verifyIfCustomerAlreadyAddedCardService) {
        this.paymentMethodsDAO = paymentMethodsDAO;
        this.creditCardValidationService = creditCardValidationService;
        this.verifyIfCustomerAlreadyAddedCardService = verifyIfCustomerAlreadyAddedCardService;
    }

    public void addCreditCard(PaymentMethodsDTO methodDTO) throws ExpiredCardException, CardAlreadyAddedException {
        creditCardValidationService.validateCardNumber(methodDTO.getCardNumber());
        creditCardValidationService.validateCvv(methodDTO.getCvv());
        creditCardValidationService.validateExpiryDate(methodDTO.getExpiryDate());
        verifyIfCustomerAlreadyAddedCardService.verifyIfCustomerAlreadyAddedCard(methodDTO);
        PaymentMethodsEntity entity = new PaymentMethodsEntity();
        entity.setCardNumber(methodDTO.getCardNumber());
        entity.setCvv(methodDTO.getCvv());
        entity.setExpiryDate(methodDTO.getExpiryDate());
        entity.setCustomer(methodDTO.getCustomer());
        paymentMethodsDAO.addMethod(entity);
    }
}
