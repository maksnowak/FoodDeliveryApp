package pap2023z.z09.paymentMethods;


import pap2023z.z09.database.PaymentMethodsEntity;

import java.sql.Date;

public class PaymentMethodsDTO {

    //getters
    private int cardNumber;
    private Date expiryDate;
    private int cvv;
    private int customer;

    public PaymentMethodsDTO(int cardNumber, Date expiryDate, int cvv, int customer)
    {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.customer = customer;
    }

    public PaymentMethodsDTO(){

    }
    public int getCardNumber(){
        return cardNumber;
    }

    public Date getExpiryDate(){
        return expiryDate;
    }

    public int getCvv(){
        return cvv;
    }

    public int getCustomer()
    {
        return customer;
    }
    //setters
    public void setCvv(int cvv){
        this.cvv = cvv;
    }

    public void setCustomer(int customer){
        this.customer = customer;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public static PaymentMethodsDTO fromEntity(PaymentMethodsEntity entity) {
        return new PaymentMethodsDTO(
                entity.getCardNumber(),
                entity.getExpiryDate(),
                entity.getCvv(),
                entity.getCustomer()
        );
    }
}