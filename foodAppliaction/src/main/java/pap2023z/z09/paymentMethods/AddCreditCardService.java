package pap2023z.z09.paymentMethods;

import pap2023z.z09.database.PaymentMethodsEntity;

public class AddCreditCardService {
    private final PaymentMethodsDAO paymentMethodsDAO;
    private final CreditCardValidationService creditCardValidationService;

    public AddCreditCardService(PaymentMethodsDAO paymentMethodsDAO, CreditCardValidationService creditCardValidationService) {
        this.paymentMethodsDAO = paymentMethodsDAO;
        this.creditCardValidationService = creditCardValidationService;
    }

    public void addCreditCard(PaymentMethodsDTO methodDTO) throws ExpiredCardException {
        creditCardValidationService.validateCardNumber(methodDTO.getCardNumber());
        creditCardValidationService.validateCvv(methodDTO.getCvv());
        creditCardValidationService.validateExpiryDate(methodDTO.getExpiryDate());
        PaymentMethodsEntity entity = new PaymentMethodsEntity();
        entity.setCardNumber(methodDTO.getCardNumber());
        entity.setCvv(methodDTO.getCvv());
        entity.setExpiryDate(methodDTO.getExpiryDate());
        entity.setCustomer(methodDTO.getCustomer());
        paymentMethodsDAO.addMethod(entity);
    }
}
