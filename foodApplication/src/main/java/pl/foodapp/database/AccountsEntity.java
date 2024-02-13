package pl.foodapp.database;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "accounts", schema = "public", catalog = "postgres")
public class AccountsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "account_id", nullable = false)
    private int accountId;
    @Basic
    @Column(name = "email", nullable = false, length = 30)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    @Basic
    @Column(name = "type", nullable = false)
    private int type;
    @Basic
    @Column(name = "name", nullable = true, length = 20)
    private String name;
    @Basic
    @Column(name = "surname", nullable = true, length = 20)
    private String surname;
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "type_id", nullable = false, insertable = false, updatable = false)
    private AccountTypesEntity accountTypesByType;
    @OneToMany(mappedBy = "accountsByCustomer")
    private Collection<BasketsEntity> basketsByAccountId;
    @OneToMany(mappedBy = "accountsByCustomer")
    private Collection<FavoritesEntity> favoritesByAccountId;
    @OneToMany(mappedBy = "accountsByCustomer")
    private Collection<OrdersEntity> ordersByAccountId;
    @OneToMany(mappedBy = "accountsByCustomer")
    private Collection<PaymentMethodsEntity> paymentMethodsByAccountId;
    @OneToMany(mappedBy = "accountsByCustomer")
    private Collection<ReviewsEntity> reviewsByAccountId;
    @OneToMany(mappedBy = "accountsByWorker")
    private Collection<WorkersEntity> workersByAccountId;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountsEntity that = (AccountsEntity) o;
        return accountId == that.accountId && type == that.type && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, email, password, type, name, surname);
    }

    public AccountTypesEntity getAccountTypesByType() {
        return accountTypesByType;
    }

    public void setAccountTypesByType(AccountTypesEntity accountTypesByType) {
        this.accountTypesByType = accountTypesByType;
    }

    public Collection<BasketsEntity> getBasketsByAccountId() {
        return basketsByAccountId;
    }

    public void setBasketsByAccountId(Collection<BasketsEntity> basketsByAccountId) {
        this.basketsByAccountId = basketsByAccountId;
    }

    public Collection<FavoritesEntity> getFavoritesByAccountId() {
        return favoritesByAccountId;
    }

    public void setFavoritesByAccountId(Collection<FavoritesEntity> favoritesByAccountId) {
        this.favoritesByAccountId = favoritesByAccountId;
    }

    public Collection<OrdersEntity> getOrdersByAccountId() {
        return ordersByAccountId;
    }

    public void setOrdersByAccountId(Collection<OrdersEntity> ordersByAccountId) {
        this.ordersByAccountId = ordersByAccountId;
    }

    public Collection<PaymentMethodsEntity> getPaymentMethodsByAccountId() {
        return paymentMethodsByAccountId;
    }

    public void setPaymentMethodsByAccountId(Collection<PaymentMethodsEntity> paymentMethodsByAccountId) {
        this.paymentMethodsByAccountId = paymentMethodsByAccountId;
    }

    public Collection<ReviewsEntity> getReviewsByAccountId() {
        return reviewsByAccountId;
    }

    public void setReviewsByAccountId(Collection<ReviewsEntity> reviewsByAccountId) {
        this.reviewsByAccountId = reviewsByAccountId;
    }

    public Collection<WorkersEntity> getWorkersByAccountId() {
        return workersByAccountId;
    }

    public void setWorkersByAccountId(Collection<WorkersEntity> workersByAccountId) {
        this.workersByAccountId = workersByAccountId;
    }
}
