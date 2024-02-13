package pap2023z.z09.database;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "workers", schema = "public", catalog = "postgres")
public class WorkersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "worker", nullable = false)
    private int worker;
    @Basic
    @Column(name = "restaurant_id", nullable = true)
    private Integer restaurantId;
    @ManyToOne
    @JoinColumn(name = "worker", referencedColumnName = "account_id", nullable = false, insertable = false, updatable = false)
    private AccountsEntity accountsByWorker;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id", insertable = false, updatable = false)
    private RestaurantsEntity restaurantsByRestaurantId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorker() {
        return worker;
    }

    public void setWorker(int worker) {
        this.worker = worker;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkersEntity that = (WorkersEntity) o;
        return id == that.id && worker == that.worker && Objects.equals(restaurantId, that.restaurantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, worker, restaurantId);
    }

    public AccountsEntity getAccountsByWorker() {
        return accountsByWorker;
    }

    public void setAccountsByWorker(AccountsEntity accountsByWorker) {
        this.accountsByWorker = accountsByWorker;
    }

    public RestaurantsEntity getRestaurantsByRestaurantId() {
        return restaurantsByRestaurantId;
    }

    public void setRestaurantsByRestaurantId(RestaurantsEntity restaurantsByRestaurantId) {
        this.restaurantsByRestaurantId = restaurantsByRestaurantId;
    }
}
