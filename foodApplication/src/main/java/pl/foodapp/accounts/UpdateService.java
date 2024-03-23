package pl.foodapp.accounts;

import pl.foodapp.database.AccountsEntity;

public class UpdateService {
    private final AccountsDAO accountsDAO;
    private final InputValidationService inputValidationService;
    private final VerifyIfEmailAlreadyExistsService verifyIfEmailAlreadyExistsService;

    public UpdateService(AccountsDAO accountsDAO, InputValidationService inputValidationService, VerifyIfEmailAlreadyExistsService verifyIfEmailAlreadyExistsService) {
        this.accountsDAO = accountsDAO;
        this.inputValidationService = inputValidationService;
        this.verifyIfEmailAlreadyExistsService = verifyIfEmailAlreadyExistsService;
    }

    public void updateAccount(AccountsDTO account) throws EmailAlreadyExistsException {
        AccountsEntity existingAccount = accountsDAO.getAccountById(account.getAccountId());

        if (existingAccount == null) {
            throw new IllegalArgumentException("Account does not exist");
        }

        if (existingAccount.getEmail() != null && !existingAccount.getEmail().equals(account.getEmail())) {
            // check if new email is not already in the database
            verifyIfEmailAlreadyExistsService.verifyEmail(account.getEmail());
        }

        inputValidationService.validateEmail(account.getEmail());
        inputValidationService.validatePassword(account.getPassword());

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
