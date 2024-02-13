package pl.foodapp.statuses;
import pl.foodapp.database.StatusesEntity;

public class StatusesDTO {

    private int statusId;
    private String name;

    public StatusesDTO(int statusId, String name){
        this.statusId = statusId;
        this.name = name;
    }

    public StatusesDTO(){

    }

    public int getStatusId() {
        return statusId;
    }

    public String getName() {
        return name;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static StatusesDTO fromEntity(StatusesEntity entity) {
        return new StatusesDTO(
                entity.getStatusId(),
                entity.getName()
        );
    }
}
