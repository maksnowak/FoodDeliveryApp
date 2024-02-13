package pl.foodapp.paymentMethods;

import pl.foodapp.database.PaymentMethodsEntity;

public class DeleteCreditCardService {
    private final PaymentMethodsDAO paymentMethodsDAO;

    public DeleteCreditCardService(PaymentMethodsDAO paymentMethodsDAO) {
        this.paymentMethodsDAO = paymentMethodsDAO;
    }

    public void deleteCreditCard(int id) {
        PaymentMethodsEntity entity = paymentMethodsDAO.getMethodIdById(id);
        if (entity != null) {
            paymentMethodsDAO.deleteMethod(entity);
        }
    }
}
