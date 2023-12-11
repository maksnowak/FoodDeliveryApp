package pap2023z.z09.database;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "baskets", schema = "public", catalog = "postgres")
public class BasketsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "customer", nullable = false)
    private int customer;
    @Basic
    @Column(name = "dish_id", nullable = false)
    private int dishId;
    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "account_id", nullable = false, insertable = false, updatable = false)
    private AccountsEntity accountsByCustomer;
    @ManyToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "dish_id", nullable = false, insertable = false, updatable = false)
    private DishesEntity dishesByDishId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketsEntity that = (BasketsEntity) o;
        return id == that.id && customer == that.customer && dishId == that.dishId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, dishId);
    }

    public AccountsEntity getAccountsByCustomer() {
        return accountsByCustomer;
    }

    public void setAccountsByCustomer(AccountsEntity accountsByCustomer) {
        this.accountsByCustomer = accountsByCustomer;
    }

    public DishesEntity getDishesByDishId() {
        return dishesByDishId;
    }

    public void setDishesByDishId(DishesEntity dishesByDishId) {
        this.dishesByDishId = dishesByDishId;
    }
}
