package pap2023z.z09.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pap2023z.z09.database.AccountsEntity;
import pap2023z.z09.database.OrdersEntity;
import pap2023z.z09.database.PaymentMethodsEntity;
import pap2023z.z09.orders.OrdersDAO;
import pap2023z.z09.paymentMethods.PaymentMethodsDAO;

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
    private PaymentMethodsDAO paymentMethodsDAO;

    @Mock
    private AccountsEntity account;

    @Mock
    private OrdersEntity order;

    @Mock
    private PaymentMethodsEntity method;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteAccount() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of());
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of());
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteAccountWithOrders() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of(order));
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of());
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteAccountWithPaymentMethods() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of());
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of(method));
        deleteService.deleteAccount(1);
        verify(accountsDAO, times(1)).deleteAccount(account);
    }

    @Test
    public void testDeleteAccountWithOrdersAndPaymentMethods() {
        when(accountsDAO.getAccountById(1)).thenReturn(account);
        when(ordersDAO.getAllOrdersFromAccountId(1)).thenReturn(List.of(order));
        when(paymentMethodsDAO.getMethodsByCustomerId(1)).thenReturn(List.of(method));
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
