package pap2023z.z09.database;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "public", catalog = "postgres")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private int orderId;
    @Basic
    @Column(name = "status")
    private int status;
    @Basic
    @Column(name = "customer")
    private int customer;
    @Basic
    @Column(name = "total")
    private BigDecimal total;
    @Basic
    @Column(name = "payment_method")
    private int paymentMethod;
    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "street_number")
    private int streetNumber;
    @Basic
    @Column(name = "apartment")
    private Integer apartment;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "discount")
    private Integer discount;
    @Basic
    @Column(name = "tip")
    private BigDecimal tip;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public BigDecimal getTip() {
        return tip;
    }

    public void setTip(BigDecimal tip) {
        this.tip = tip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return orderId == that.orderId && status == that.status && customer == that.customer && paymentMethod == that.paymentMethod && streetNumber == that.streetNumber && Objects.equals(total, that.total) && Objects.equals(street, that.street) && Objects.equals(apartment, that.apartment) && Objects.equals(city, that.city) && Objects.equals(discount, that.discount) && Objects.equals(tip, that.tip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, status, customer, total, paymentMethod, street, streetNumber, apartment, city, discount, tip);
    }
}
