package pap2023z.z09.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.AccountTypesEntity;
import pap2023z.z09.database.AccountsEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ViewAccountTypeNameServiceTest {
    @InjectMocks
    private ViewAccountTypeNameService viewAccountTypeNameService;

    @Mock
    private AccountTypesDAO accountTypesDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAccountTypeNameTest() {
        AccountTypesEntity testEntity = new AccountTypesEntity();
        testEntity.setTypeId(1);
        testEntity.setName("name");
        when(accountTypesDAO.getAccountTypeById(1)).thenReturn(testEntity);
        assertEquals("name", viewAccountTypeNameService.getAccountTypeName(1));
    }
}
