package pap2023z.z09.accounts;

public class VerifyIfEmailAlreadyExistsService {
    private final AccountsDAO accountsDAO;

    public VerifyIfEmailAlreadyExistsService(AccountsDAO accountsDAO) {
        this.accountsDAO = accountsDAO;
    }

    public void verifyEmail(String email) throws EmailAlreadyExistsException {
        if (accountsDAO.getAccountByEmail(email) != null) {
            throw new EmailAlreadyExistsException();
        }
    }
}
