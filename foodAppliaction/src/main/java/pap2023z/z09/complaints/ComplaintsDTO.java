package pap2023z.z09.complaints;

import pap2023z.z09.database.ComplaintsEntity;

public class ComplaintsDTO {
    private int complaintId;
    private int orderId;
    private String description;
    private boolean isOpen;

    public ComplaintsDTO(int complaintId, int orderId, String description, boolean isOpen){
        this.complaintId = complaintId;
        this.orderId = orderId;
        this.description = description;
        this.isOpen = isOpen;
    }

    public ComplaintsDTO() {

    }
    //getters

    public int getComplaintId() {
        return complaintId;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOpen() {
        return isOpen;
    }

    //setters

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }


    public static ComplaintsDTO fromEntity(ComplaintsEntity entity) {
        return new ComplaintsDTO(
            entity.getComplaintId(),
            entity.getOrderId(),
            entity.getDescription(),
            entity.isOpen()
        );
    }
}

