package pap2023z.z09.database;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "payment_methods", schema = "public", catalog = "postgres")
public class PaymentMethodsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "method_id", nullable = false)
    private int methodId;
    @Basic
    @Column(name = "card_number", nullable = false, precision = 0)
    private int cardNumber;
    @Basic
    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;
    @Basic
    @Column(name = "cvv", nullable = false, precision = 0)
    private int cvv;
    @Basic
    @Column(name = "customer", nullable = false)
    private int customer;

    public int getMethodId() {
        return methodId;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethodsEntity that = (PaymentMethodsEntity) o;
        return methodId == that.methodId && cardNumber == that.cardNumber && cvv == that.cvv && customer == that.customer && Objects.equals(expiryDate, that.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodId, cardNumber, expiryDate, cvv, customer);
    }
}
