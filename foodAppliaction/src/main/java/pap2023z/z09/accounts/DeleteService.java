package pap2023z.z09.accounts;

import pap2023z.z09.database.AccountsEntity;

public class DeleteService {
    private final AccountsDAO accountsDAO;

    public DeleteService(AccountsDAO accountsDAO) {
        this.accountsDAO = accountsDAO;
    }

    public void deleteAccount(int id) {
        AccountsEntity account = accountsDAO.getAccountById(id);
        if (account != null) {
            accountsDAO.deleteAccount(account);
        }
    }
}
