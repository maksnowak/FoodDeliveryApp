package pap2023z.z09.database;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "workers", schema = "public", catalog = "postgres")
public class WorkersEntity {
    @Basic
    @Column(name = "worker", nullable = false)
    private int worker;
    @Basic
    @Column(name = "restaurant_id", nullable = true)
    private Integer restaurantId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

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
        return worker == that.worker && Objects.equals(restaurantId, that.restaurantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worker, restaurantId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
