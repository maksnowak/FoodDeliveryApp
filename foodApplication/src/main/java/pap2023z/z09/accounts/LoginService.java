package pap2023z.z09.accounts;

import pap2023z.z09.database.AccountsEntity;

public class LoginService {
    private final AccountsDAO accountsDAO;

    public LoginService(AccountsDAO accountsDAO) {
        this.accountsDAO = accountsDAO;
    }

    public boolean login(String email, String password) {
        // sprawdź czy konto o podanym emailu istnieje, jeśli nie to zwróć false
        // jeśli istnieje to sprawdź czy hasło się zgadza
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
