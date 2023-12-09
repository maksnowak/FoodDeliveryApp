package pap2023z.z09.accounts;

import pap2023z.z09.database.AccountsEntity;

public class SignUpService {
    private final AccountsDAO accountsDAO;

    public SignUpService(AccountsDAO accountsDAO) {
        this.accountsDAO = accountsDAO;
    }

    public void signUp(AccountsDTO account) throws EmailAlreadyExistsException {
        validateInput(account.getEmail(), account.getPassword());
        verifyEmail(account.getEmail());
        AccountsEntity entity = new AccountsEntity();
        entity.setEmail(account.getEmail());
        entity.setPassword(account.getPassword());
        entity.setType(account.getType());
        entity.setName(account.getName());
        entity.setSurname(account.getSurname());
        accountsDAO.addAccount(entity);
    }

    public void verifyEmail(String email) throws EmailAlreadyExistsException {
        if (accountsDAO.getAccountByEmail(email) != null) {
            throw new EmailAlreadyExistsException();
        }
    }

    public void validateInput(String email, String password) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }
}
