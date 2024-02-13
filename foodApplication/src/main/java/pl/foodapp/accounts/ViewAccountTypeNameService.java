package pl.foodapp.accounts;

public class ViewAccountTypeNameService {
    private final AccountTypesDAO accountTypesDAO;

    public ViewAccountTypeNameService(AccountTypesDAO accountTypesDAO) {
        this.accountTypesDAO = accountTypesDAO;
    }

    public String getAccountTypeName(int typeId) {
        return accountTypesDAO.getAccountTypeById(typeId).getName();
    }
}
