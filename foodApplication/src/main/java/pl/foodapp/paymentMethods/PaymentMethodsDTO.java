package pl.foodapp.paymentMethods;


import pl.foodapp.database.PaymentMethodsEntity;

import java.sql.Date;

public class PaymentMethodsDTO {

    //getters
    private String cardNumber;
    private Date expiryDate;
    private String cvv;
    private int customer;
    private int MethodId;
    public PaymentMethodsDTO(int MethodId, String cardNumber, Date expiryDate, String cvv, int customer)
    {
        this.MethodId = MethodId;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.customer = customer;
    }

    public PaymentMethodsDTO(){

    }
    public int getMethodId(){
        return MethodId;
    }
    public String getCardNumber(){
        return cardNumber;
    }

    public Date getExpiryDate(){
        return expiryDate;
    }

    public String getCvv(){
        return cvv;
    }

    public int getCustomer()
    {
        return customer;
    }
    //setters
    public void setCvv(String cvv){
        this.cvv = cvv;
    }

    public void setCustomer(int customer){
        this.customer = customer;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setMethodId(int MethodId) {
        this.MethodId = MethodId;
    }

    public static PaymentMethodsDTO fromEntity(PaymentMethodsEntity entity) {
        return new PaymentMethodsDTO(
                entity.getMethodId(),
                entity.getCardNumber(),
                entity.getExpiryDate(),
                entity.getCvv(),
                entity.getCustomer()
        );
    }
}