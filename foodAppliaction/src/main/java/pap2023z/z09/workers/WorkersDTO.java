package pap2023z.z09.workers;

import pap2023z.z09.database.WorkersEntity;

public class WorkersDTO {
    private int id;
    private int worker;
    private int restaurantId;

    public WorkersDTO(int id, int worker, int restaurantId) {
        this.id = id;
        this.worker = worker;
        this.restaurantId = restaurantId;
    }

    public WorkersDTO(){

    }

    public int getId() {
        return id;
    }

    public int getWorker() {
        return worker;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWorker(int worker) {
        this.worker = worker;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public static WorkersDTO fromEntity(WorkersEntity entity) {
        return new WorkersDTO(
                entity.getId(),
                entity.getWorker(),
                entity.getRestaurantId()
        );
    }
}
