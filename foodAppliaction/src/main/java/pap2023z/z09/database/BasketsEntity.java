package pap2023z.z09.database;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "baskets", schema = "public", catalog = "postgres")
public class BasketsEntity {
    @Basic
    @Column(name = "customer")
    private int customer;
    @Basic
    @Column(name = "dish_id")
    private int dishId;

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
        return customer == that.customer && dishId == that.dishId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, dishId);
    }
}
