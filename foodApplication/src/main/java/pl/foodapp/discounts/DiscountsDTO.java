package pl.foodapp.discounts;

import pl.foodapp.database.DiscountsEntity;

import java.math.BigDecimal;

public class DiscountsDTO {
    private int discountId;
    private String code;
    private BigDecimal discount;

    public DiscountsDTO(int discountId, String code, BigDecimal discount) {
        this.discountId = discountId;
        this.code = code;
        this.discount = discount;
    }

    public DiscountsDTO() {
    }

    public int getDiscountId() {
        return discountId;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public static DiscountsDTO fromEntity(DiscountsEntity discountsEntity) {
        return new DiscountsDTO(
                discountsEntity.getDiscountId(),
                discountsEntity.getCode(),
                discountsEntity.getDiscount()
        );
    }
}
