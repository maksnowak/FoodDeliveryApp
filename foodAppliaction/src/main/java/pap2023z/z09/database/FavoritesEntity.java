package pap2023z.z09.database;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "favorites", schema = "public", catalog = "postgres")
public class FavoritesEntity {
    @Basic
    @Column(name = "dish_id", nullable = false)
    private int dishId;
    @Basic
    @Column(name = "customer", nullable = false)
    private int customer;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

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
        return dishId == that.dishId && customer == that.customer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, customer);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
