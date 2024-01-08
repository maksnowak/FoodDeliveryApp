package pap2023z.z09.baskets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.BasketsEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class BasketsDTOTest {
    @Mock
    private BasketsEntity basketsEntity;
    private BasketsDTO basketsDTO;
    public static int exampleInt = 1;
    public static int exampleInt2 = 2;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        basketsDTO = new BasketsDTO();
    }
    @Test
    public void setDishIdTest() {
        basketsDTO.setDishId(exampleInt);
        assertEquals(exampleInt, basketsDTO.getDishId());
    }

    @Test
    public void setCustomerIdTest() {
        basketsDTO.setCustomerId(exampleInt);
        assertEquals(exampleInt, basketsDTO.getCustomerId());
    }

    @Test
    public void fromEntityTest() {

        when(basketsEntity.getDishId()).thenReturn(exampleInt);
        when(basketsEntity.getCustomer()).thenReturn(exampleInt2);

        basketsDTO = new BasketsDTO().fromEntity(basketsEntity);

        assertEquals(exampleInt, basketsDTO.getDishId());
        assertEquals(exampleInt2, basketsDTO.getCustomerId());

    }
}


/*



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

 */