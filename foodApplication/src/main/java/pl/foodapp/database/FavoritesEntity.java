package pl.foodapp.database;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "favorites", schema = "public", catalog = "postgres")
public class FavoritesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "dish_id", nullable = false)
    private int dishId;
    @Basic
    @Column(name = "customer", nullable = false)
    private int customer;
    @ManyToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "dish_id", nullable = false, insertable = false, updatable = false)
    private DishesEntity dishesByDishId;
    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "account_id", nullable = false, insertable = false, updatable = false)
    private AccountsEntity accountsByCustomer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
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
        FavoritesEntity that = (FavoritesEntity) o;
        return id == that.id && dishId == that.dishId && customer == that.customer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dishId, customer);
    }

    public DishesEntity getDishesByDishId() {
        return dishesByDishId;
    }

    public void setDishesByDishId(DishesEntity dishesByDishId) {
        this.dishesByDishId = dishesByDishId;
    }

    public AccountsEntity getAccountsByCustomer() {
        return accountsByCustomer;
    }

    public void setAccountsByCustomer(AccountsEntity accountsByCustomer) {
        this.accountsByCustomer = accountsByCustomer;
    }
}
