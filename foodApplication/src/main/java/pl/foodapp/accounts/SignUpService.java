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
        // sprawdzanie poprawności danych oraz czy konto o podanym emailu już istnieje
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
