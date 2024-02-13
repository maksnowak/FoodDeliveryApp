package pap2023z.z09.database;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "dishes", schema = "public", catalog = "postgres")
public class DishesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dish_id", nullable = false)
    private int dishId;
    @Basic
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @Basic
    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;
    @Basic
    @Column(name = "type_id", nullable = false)
    private int typeId;
    @Basic
    @Column(name = "vegetarian", nullable = false)
    private boolean vegetarian;
    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    private BigDecimal price;
    @Basic
    @Column(name = "kcal", nullable = false, precision = 1)
    private BigDecimal kcal;
    @OneToMany(mappedBy = "dishesByDishId")
    private Collection<BasketsEntity> basketsByDishId;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id", nullable = false, insertable = false, updatable = false)
    private RestaurantsEntity restaurantsByRestaurantId;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "type_id", nullable = false, insertable = false, updatable = false)
    private DishTypesEntity dishTypesByTypeId;
    @OneToMany(mappedBy = "dishesByDishId")
    private Collection<FavoritesEntity> favoritesByDishId;
    @OneToMany(mappedBy = "dishesByDishId")
    private Collection<OrderedDishesEntity> orderedDishesByDishId;

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

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
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
        return dishId == that.dishId && restaurantId == that.restaurantId && typeId == that.typeId && vegetarian == that.vegetarian && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(kcal, that.kcal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, name, restaurantId, typeId, vegetarian, price, kcal);
    }

    public Collection<BasketsEntity> getBasketsByDishId() {
        return basketsByDishId;
    }

    public void setBasketsByDishId(Collection<BasketsEntity> basketsByDishId) {
        this.basketsByDishId = basketsByDishId;
    }

    public RestaurantsEntity getRestaurantsByRestaurantId() {
        return restaurantsByRestaurantId;
    }

    public void setRestaurantsByRestaurantId(RestaurantsEntity restaurantsByRestaurantId) {
        this.restaurantsByRestaurantId = restaurantsByRestaurantId;
    }

    public DishTypesEntity getDishTypesByTypeId() {
        return dishTypesByTypeId;
    }

    public void setDishTypesByTypeId(DishTypesEntity dishTypesByTypeId) {
        this.dishTypesByTypeId = dishTypesByTypeId;
    }

    public Collection<FavoritesEntity> getFavoritesByDishId() {
        return favoritesByDishId;
    }

    public void setFavoritesByDishId(Collection<FavoritesEntity> favoritesByDishId) {
        this.favoritesByDishId = favoritesByDishId;
    }

    public Collection<OrderedDishesEntity> getOrderedDishesByDishId() {
        return orderedDishesByDishId;
    }

    public void setOrderedDishesByDishId(Collection<OrderedDishesEntity> orderedDishesByDishId) {
        this.orderedDishesByDishId = orderedDishesByDishId;
    }
}
