package pap2023z.z09.database;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "dishes", schema = "public", catalog = "postgres")
public class DishesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dish_id")
    private int dishId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "restaurant_id")
    private int restaurantId;
    @Basic
    @Column(name = "type_id")
    private int typeId;
    @Basic
    @Column(name = "vegetarian")
    private Boolean vegetarian;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "kcal")
    private BigDecimal kcal;

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getKcal() {
        return kcal;
    }

    public void setKcal(BigDecimal kcal) {
        this.kcal = kcal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishesEntity that = (DishesEntity) o;
        return dishId == that.dishId && restaurantId == that.restaurantId && typeId == that.typeId && Objects.equals(name, that.name) && Objects.equals(vegetarian, that.vegetarian) && Objects.equals(price, that.price) && Objects.equals(kcal, that.kcal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, name, restaurantId, typeId, vegetarian, price, kcal);
    }
}
