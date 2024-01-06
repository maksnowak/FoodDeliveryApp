package pap2023z.z09.baskets;

import pap2023z.z09.database.BasketsEntity;

public class BasketsDTO {

    private int customerID;
    private int dishId;
    public BasketsDTO(int customerID, int dishId)
    {
        this.customerID = customerID;
        this.dishId = dishId;
    }
    public BasketsDTO()
    {

    }
    public int getCustomerID() {
        return customerID;
    }

    public int getDishId() {
        return dishId;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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


/*

    public static OrderedDishsesDTO fromEntity(OrderedDishesEntity entity) {
        return new OrderedDishsesDTO(
                entity.getOrderId(),
                entity.getDishId()
        );
    }
}

 */