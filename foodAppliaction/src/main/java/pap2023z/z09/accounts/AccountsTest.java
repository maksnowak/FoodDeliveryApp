package pap2023z.z09.accounts;

public class AccountsTest {
    public static void main (String[] args) {
        AccountsDAO accountDAO = new AccountsDAO();
        System.out.println("DAO test");
        System.out.println(accountDAO.getAllAccounts());
        System.out.println(accountDAO.getAccountById(1));
        System.out.println(accountDAO.getAccountByEmail("admin@example.com"));
        System.out.println();
        System.out.println("DTO test");
        AccountsDTO dto = AccountsDTO.fromEntity(accountDAO.getAccountById(1));
        System.out.println(dto.getAccountId());
        System.out.println(dto.getEmail());
        System.out.println(dto.getPassword());
        System.out.println(dto.getType());
        System.out.println(dto.getName());
        System.out.println(dto.getSurname());

        System.out.println("Login test");
        LoginService loginService = new LoginService();
        System.out.println(loginService.login("admin@example.com", "admin"));
        System.out.println(loginService.login("admin@example.com", "wrong"));
        System.out.println(loginService.login("not_admin@example.com", "admin"));
        System.out.println("DTO conversion test");
        AccountsDTO dto2 = loginService.convertToDTO(accountDAO.getAccountByEmail("admin@example.com"));
        System.out.println(dto2.getAccountId());
        System.out.println(dto2.getEmail());
        System.out.println(dto2.getPassword());
        System.out.println(dto2.getType());
        System.out.println(dto2.getName());
        System.out.println(dto2.getSurname());
    }
}
