package pap2023z.z09.database;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "statuses", schema = "public", catalog = "postgres")
public class StatusesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "status_id", nullable = false)
    private int statusId;
    @Basic
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @OneToMany(mappedBy = "statusesByStatus")
    private Collection<OrdersEntity> ordersByStatusId;

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusesEntity that = (StatusesEntity) o;
        return statusId == that.statusId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, name);
    }

    public Collection<OrdersEntity> getOrdersByStatusId() {
        return ordersByStatusId;
    }

    public void setOrdersByStatusId(Collection<OrdersEntity> ordersByStatusId) {
        this.ordersByStatusId = ordersByStatusId;
    }
}
