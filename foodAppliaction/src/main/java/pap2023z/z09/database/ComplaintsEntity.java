package pap2023z.z09.database;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "complaints", schema = "public", catalog = "postgres")
public class ComplaintsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "complaint_id")
    private int complaintId;
    @Basic
    @Column(name = "order_id")
    private int orderId;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "is_open")
    private boolean isOpen;

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplaintsEntity that = (ComplaintsEntity) o;
        return complaintId == that.complaintId && orderId == that.orderId && isOpen == that.isOpen && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(complaintId, orderId, description, isOpen);
    }
}
