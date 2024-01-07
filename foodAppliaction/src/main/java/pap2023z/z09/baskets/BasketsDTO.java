package pap2023z.z09.baskets;

import pap2023z.z09.database.BasketsEntity;

public class BasketsDTO {

    private int customerId;
    private int dishId;
    public BasketsDTO(int customerId, int dishId)
    {
        this.customerId = customerId;
        this.dishId = dishId;
    }
    public BasketsDTO()
    {

    }
    public int getCustomerId() {
        return customerId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setCustomerId(int customerID) {
        this.customerId = customerID;
    }
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public static BasketsDTO fromEntity(BasketsEntity entity) {
        return new BasketsDTO(
                entity.getCustomer(),
                entity.getDishId()
        );
    }
}