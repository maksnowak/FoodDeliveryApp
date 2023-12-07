package pap2023z.z09.database;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "statuses", schema = "public", catalog = "postgres")
public class StatusesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "status_id")
    private int statusId;
    @Basic
    @Column(name = "name")
    private String name;

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
}
