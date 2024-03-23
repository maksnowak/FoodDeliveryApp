package pl.foodapp.accounts;

import pl.foodapp.database.AccountsEntity;

public class LoginService {
    private final AccountsDAO accountsDAO;

    public LoginService(AccountsDAO accountsDAO) {
        this.accountsDAO = accountsDAO;
    }

    public boolean login(String email, String password) {
        // check if account with given email exists, if not return false
        // if it exists, check if password is correct
        AccountsEntity account = accountsDAO.getAccountByEmail(email);
        if (account == null) {
            return false;
        }
        return account.getPassword().equals(password);
    }

    public AccountsDTO convertToDTO(AccountsEntity entity) {
        return AccountsDTO.fromEntity(entity);
    }
}
