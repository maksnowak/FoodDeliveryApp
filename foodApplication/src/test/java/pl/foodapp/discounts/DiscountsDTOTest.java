package pl.foodapp.discounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.foodapp.database.DiscountsEntity;
import pl.foodapp.discounts.DiscountsDTO;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DiscountsDTOTest {
    private DiscountsDTO discountsDTO;

    @Mock
    private DiscountsEntity discountsEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        discountsDTO = new DiscountsDTO();
    }

    @Test
    public void testSetAndGetDiscountId() {
        int discountId = 1;
        discountsDTO.setDiscountId(discountId);
        assertEquals(discountId, discountsDTO.getDiscountId());
    }

    @Test
    public void testSetAndGetCode() {
        String code = "test";
        discountsDTO.setCode(code);
        assertEquals(code, discountsDTO.getCode());
    }

    @Test
    public void testSetAndGetDiscount() {
        BigDecimal discount = BigDecimal.valueOf(0.1);
        discountsDTO.setDiscount(discount);
        assertEquals(discount, discountsDTO.getDiscount());
    }

    @Test
    public void testFromEntity() {
        int discountId = 1;
        String code = "test";
        BigDecimal discount = BigDecimal.valueOf(0.1);
        when(discountsEntity.getDiscountId()).thenReturn(discountId);
        when(discountsEntity.getCode()).thenReturn(code);
        when(discountsEntity.getDiscount()).thenReturn(discount);
        discountsDTO = DiscountsDTO.fromEntity(discountsEntity);
        assertEquals(discountId, discountsDTO.getDiscountId());
        assertEquals(code, discountsDTO.getCode());
        assertEquals(discount, discountsDTO.getDiscount());
    }
}
