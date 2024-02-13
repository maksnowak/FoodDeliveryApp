package pl.foodapp.database;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "public", catalog = "postgres")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id", nullable = false)
    private int orderId;
    @Basic
    @Column(name = "status", nullable = false)
    private int status;
    @Basic
    @Column(name = "customer", nullable = true)
    private Integer customer;
    @Basic
    @Column(name = "total", nullable = false, precision = 2)
    private BigDecimal total;
    @Basic
    @Column(name = "payment_method", nullable = true)
    private Integer paymentMethod;
    @Basic
    @Column(name = "street", nullable = false, length = 40)
    private String street;
    @Basic
    @Column(name = "street_number", nullable = false)
    private int streetNumber;
    @Basic
    @Column(name = "apartment", nullable = true)
    private Integer apartment;
    @Basic
    @Column(name = "city", nullable = false, length = 40)
    private String city;
    @Basic
    @Column(name = "discount", nullable = true)
    private Integer discount;
    @Basic
    @Column(name = "tip", nullable = true, precision = 2)
    private BigDecimal tip;
    @OneToMany(mappedBy = "ordersByOrderId")
    private Collection<ComplaintsEntity> complaintsByOrderId;
    @OneToMany(mappedBy = "ordersByOrderId")
    private Collection<OrderedDishesEntity> orderedDishesByOrderId;
    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "status_id", nullable = false, insertable = false, updatable = false)
    private StatusesEntity statusesByStatus;
    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "account_id", insertable = false, updatable = false)
    private AccountsEntity accountsByCustomer;
    @ManyToOne
    @JoinColumn(name = "payment_method", referencedColumnName = "method_id", insertable = false, updatable = false)
    private PaymentMethodsEntity paymentMethodsByPaymentMethod;
    @ManyToOne
    @JoinColumn(name = "discount", referencedColumnName = "discount_id", insertable = false, updatable = false)
    private DiscountsEntity discountsByDiscount;

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

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
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
        return orderId == that.orderId && status == that.status && streetNumber == that.streetNumber && Objects.equals(customer, that.customer) && Objects.equals(total, that.total) && Objects.equals(paymentMethod, that.paymentMethod) && Objects.equals(street, that.street) && Objects.equals(apartment, that.apartment) && Objects.equals(city, that.city) && Objects.equals(discount, that.discount) && Objects.equals(tip, that.tip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, status, customer, total, paymentMethod, street, streetNumber, apartment, city, discount, tip);
    }

    public Collection<ComplaintsEntity> getComplaintsByOrderId() {
        return complaintsByOrderId;
    }

    public void setComplaintsByOrderId(Collection<ComplaintsEntity> complaintsByOrderId) {
        this.complaintsByOrderId = complaintsByOrderId;
    }

    public Collection<OrderedDishesEntity> getOrderedDishesByOrderId() {
        return orderedDishesByOrderId;
    }

    public void setOrderedDishesByOrderId(Collection<OrderedDishesEntity> orderedDishesByOrderId) {
        this.orderedDishesByOrderId = orderedDishesByOrderId;
    }

    public StatusesEntity getStatusesByStatus() {
        return statusesByStatus;
    }

    public void setStatusesByStatus(StatusesEntity statusesByStatus) {
        this.statusesByStatus = statusesByStatus;
    }

    public AccountsEntity getAccountsByCustomer() {
        return accountsByCustomer;
    }

    public void setAccountsByCustomer(AccountsEntity accountsByCustomer) {
        this.accountsByCustomer = accountsByCustomer;
    }

    public PaymentMethodsEntity getPaymentMethodsByPaymentMethod() {
        return paymentMethodsByPaymentMethod;
    }

    public void setPaymentMethodsByPaymentMethod(PaymentMethodsEntity paymentMethodsByPaymentMethod) {
        this.paymentMethodsByPaymentMethod = paymentMethodsByPaymentMethod;
    }

    public DiscountsEntity getDiscountsByDiscount() {
        return discountsByDiscount;
    }

    public void setDiscountsByDiscount(DiscountsEntity discountsByDiscount) {
        this.discountsByDiscount = discountsByDiscount;
    }
}
