package pap2023z.z09.accounts;

import pap2023z.z09.database.AccountsEntity;

public class UpdateService {
    private final AccountsDAO accountsDAO;
    private final SignUpService signUpService;

    public UpdateService(AccountsDAO accountsDAO, SignUpService signUpService) {
        this.accountsDAO = accountsDAO;
        this.signUpService = signUpService;
    }

    public void updateAccount(AccountsDTO account) throws EmailAlreadyExistsException {
        AccountsEntity existingAccount = accountsDAO.getAccountById(account.getAccountId());

        if (existingAccount == null) {
            throw new IllegalArgumentException("Account does not exist");
        }

        if (!existingAccount.getEmail().equals(account.getEmail())) {
            signUpService.verifyEmail(account.getEmail());
        }

        AccountsEntity entity = new AccountsEntity();
        entity.setAccountId(account.getAccountId());
        entity.setEmail(account.getEmail());
        entity.setPassword(account.getPassword());
        entity.setType(account.getType());
        entity.setName(account.getName());
        entity.setSurname(account.getSurname());
        accountsDAO.updateAccount(entity);
    }
}
