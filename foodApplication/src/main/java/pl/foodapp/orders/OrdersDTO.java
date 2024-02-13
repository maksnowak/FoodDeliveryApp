package pl.foodapp.orders;

import pl.foodapp.database.OrdersEntity;

import java.math.BigDecimal;


public class OrdersDTO {
    private int orderId;
    private int statusId;
    private int customerId;
    private BigDecimal total;
    private int paymentMethodId;

    //address info
    private String street;
    private int streetNumber;
    private Integer apartment;
    private String city;
    //

    private Integer discountId;
    private BigDecimal tip;
    public OrdersDTO(
            int orderId,
            int statusId,
            int customerId,
            BigDecimal total,
            int paymentMethodId,
            String street,
            int streetNumber,
            Integer apartment,
            String city,
            Integer discountId,
            BigDecimal tip
            ){
        this.orderId = orderId;
        this.statusId = statusId;
        this.customerId = customerId;
        this.total = total;
        this.paymentMethodId = paymentMethodId;
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartment = apartment;
        this.city = city;
        this.discountId = discountId;
        this.tip = tip;
    }
    public OrdersDTO() {

    }
//getters

    public int getOrderId() {
        return orderId;
    }
    public int getStatusId() {
        return statusId;
    }

    public int getCustomerId() {
        return customerId;
    }
    public BigDecimal total() {
        return total;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public String getStreet() {
        return street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public Integer getApartment() {
        return apartment;
    }

    public String getCity() {
        return city;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public BigDecimal getTip() {
        return tip;
    }

    public BigDecimal getTotal() {
        return total;
    }

//setters

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setTip(BigDecimal tip) {
        this.tip = tip;
    }


    public static OrdersDTO fromEntity(OrdersEntity entity) {
        return new OrdersDTO(
                entity.getOrderId(),
                entity.getStatus(),
                entity.getCustomer(),
                entity.getTotal(),
                entity.getPaymentMethod(),
                entity.getStreet(),
                entity.getStreetNumber(),
                entity.getApartment(),
                entity.getCity(),
                entity.getDiscount(),
                entity.getTip()
        );
    }
}