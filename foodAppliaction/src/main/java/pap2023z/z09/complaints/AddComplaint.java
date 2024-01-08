package pap2023z.z09.complaints;

import pap2023z.z09.database.ComplaintsEntity;
import pap2023z.z09.database.OrdersEntity;
import pap2023z.z09.discounts.DiscountsDAO;
import pap2023z.z09.orders.OrdersDAO;

import java.math.BigDecimal;
import java.util.List;

public class AddComplaint {

    private final ComplaintsDAO complaintsDAO;
    public AddComplaint(ComplaintsDAO complaintsDAO){
        this.complaintsDAO = complaintsDAO;
    }

    public void addComplaint(ComplaintsDTO complaint){
        checkDescription(complaint.getDescription());
        checkOrderId(complaint.getOrderId());

        ComplaintsEntity entity = new ComplaintsEntity();

        entity.setDescription(complaint.getDescription());
        entity.setOrderId(complaint.getOrderId());
        entity.setOpen(true);

        complaintsDAO.addComplaint(entity);
    }
    public void checkDescription(String code){
        if(code.isEmpty()){
            throw new IllegalArgumentException("complaint is empty ");
        }
    }
    private void checkOrderId(int id){
        OrdersDAO orderDAO = new OrdersDAO();
        List<OrdersEntity> orders = orderDAO.getAllOrders();
        for(OrdersEntity order : orders){
            if(order.getOrderId() == id){
                return;
            }
        }
        throw new IllegalArgumentException("the foreign key OrderId does not exist in primary keys ");
    }

}