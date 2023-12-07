package pap2023z.z09.database;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "dish_types", schema = "public", catalog = "postgres")
public class DishTypesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "type_id", nullable = false)
    private int typeId;
    @Basic
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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
        DishTypesEntity that = (DishTypesEntity) o;
        return typeId == that.typeId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, name);
    }
}
