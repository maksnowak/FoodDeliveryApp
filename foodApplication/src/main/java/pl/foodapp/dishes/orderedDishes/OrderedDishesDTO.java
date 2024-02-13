package pl.foodapp.dishes.orderedDishes;
import pl.foodapp.database.OrderedDishesEntity;

public class OrderedDishesDTO
{
    private int id;
    private int orderId;
    private int dishId;
    public OrderedDishesDTO(int id, int orderId, int dishId)
    {
        this.id = id;
        this.orderId = orderId;
        this.dishId = dishId;
    }

    public OrderedDishesDTO()
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

    public static OrderedDishesDTO fromEntity(OrderedDishesEntity entity) {
        return new OrderedDishesDTO(
                entity.getId(),
                entity.getOrderId(),
                entity.getDishId()
        );
    }
}