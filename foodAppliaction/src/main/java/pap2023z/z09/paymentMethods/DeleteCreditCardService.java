package pap2023z.z09.paymentMethods;

import pap2023z.z09.database.PaymentMethodsEntity;

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
