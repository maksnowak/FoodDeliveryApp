package pap2023z.z09.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.AccountsEntity;
import pap2023z.z09.database.OrdersEntity;
import pap2023z.z09.orders.OrdersDAO;

import java.util.List;

import static org.mockito.Mockito.*;

public class DeleteServiceTest {
    @InjectMocks
    private DeleteService deleteService;

    @Mock
    private AccountsDAO accountsDAO;

    @Mock
    private OrdersDAO ordersDAO;

    @Mock
    private AccountsEntity account;

    @Mock
    private OrdersEntity order;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteAccount() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of());
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteAccountWithOrders() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of(order));
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteNoAccount() {
        when(accountsDAO.getAccountById(1)).thenReturn(null);
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(0)).deleteAccount(account);
    }
}
