package pap2023z.z09.database;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "discounts", schema = "public", catalog = "postgres")
public class DiscountsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "discount_id")
    private int discountId;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "discount")
    private BigDecimal discount;

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountsEntity that = (DiscountsEntity) o;
        return discountId == that.discountId && Objects.equals(code, that.code) && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountId, code, discount);
    }
}
