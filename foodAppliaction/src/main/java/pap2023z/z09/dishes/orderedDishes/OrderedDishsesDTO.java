package pap2023z.z09.dishes.orderedDishes;
import pap2023z.z09.database.OrderedDishesEntity;

public class OrderedDishsesDTO
{
    private int id;
    private int orderId;
    private int dishId;
    public OrderedDishsesDTO(int id, int orderId, int dishId)
    {
        this.id = id;
        this.orderId = orderId;
        this.dishId = dishId;
    }

    public OrderedDishsesDTO()
    {

    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public static OrderedDishsesDTO fromEntity(OrderedDishesEntity entity) {
        return new OrderedDishsesDTO(
                entity.getId(),
                entity.getOrderId(),
                entity.getDishId()
        );
    }
}