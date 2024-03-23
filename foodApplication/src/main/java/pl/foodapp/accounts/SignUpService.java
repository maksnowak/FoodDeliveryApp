package pl.foodapp.accounts;

import pl.foodapp.database.AccountsEntity;

public class SignUpService {
    private final AccountsDAO accountsDAO;
    private final InputValidationService inputValidationService;
    private final VerifyIfEmailAlreadyExistsService verifyIfEmailAlreadyExistsService;

    public SignUpService(AccountsDAO accountsDAO, InputValidationService inputValidationService, VerifyIfEmailAlreadyExistsService verifyIfEmailAlreadyExistsService) {
        this.accountsDAO = accountsDAO;
        this.inputValidationService = inputValidationService;
        this.verifyIfEmailAlreadyExistsService = verifyIfEmailAlreadyExistsService;
    }

    public void signUp(AccountsDTO account) throws EmailAlreadyExistsException {
        // check if data is correct and if account with given email already exists
        inputValidationService.validateEmail(account.getEmail());
        inputValidationService.validatePassword(account.getPassword());
        verifyIfEmailAlreadyExistsService.verifyEmail(account.getEmail());
        AccountsEntity entity = new AccountsEntity();
        entity.setEmail(account.getEmail());
        entity.setPassword(account.getPassword());
        entity.setType(account.getType());
        entity.setName(account.getName());
        entity.setSurname(account.getSurname());
        accountsDAO.addAccount(entity);
    }
}
