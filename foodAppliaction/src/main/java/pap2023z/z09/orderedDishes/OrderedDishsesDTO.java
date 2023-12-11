package pap2023z.z09.orderedDishes;
import pap2023z.z09.database.OrderedDishesEntity;

public class OrderedDishsesDTO
{
    private int orderId;
    private int dishId;
    public OrderedDishsesDTO(int orderId, int dishId)
    {
        this.orderId = orderId;
        this.dishId = dishId;
    }

    public OrderedDishsesDTO()
    {

    }
    public int getOrderId() {
        return orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public static OrderedDishsesDTO fromEntity(OrderedDishesEntity entity) {
        return new OrderedDishsesDTO(
                entity.getOrderId(),
                entity.getDishId()
        );
    }
}