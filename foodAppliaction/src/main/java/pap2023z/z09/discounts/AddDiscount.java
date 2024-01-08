package pap2023z.z09.discounts;

import pap2023z.z09.database.DiscountsEntity;

import java.math.BigDecimal;

public class AddDiscount {

    private final DiscountsDAO discountsDAO;
    public AddDiscount(DiscountsDAO discountsDAO){
        this.discountsDAO = discountsDAO;
    }

    public void addDiscount(DiscountsDTO discount){
        checkCode(discount.getCode());
        checkIfInRange(discount.getDiscount());

        DiscountsEntity entity = new DiscountsEntity();

        entity.setDiscount(discount.getDiscount());
        entity.setCode(discount.getCode());

        discountsDAO.addDiscount(entity);
    }
    public void checkCode(String code){
        if(code.isEmpty()){
            throw new IllegalArgumentException("code is empty ");
        }
    }
    public void checkIfInRange(BigDecimal money){
        if(money.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("one of the numbers is smaller than zero ");
        }
    }
}