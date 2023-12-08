package pap2023z.z09.accounts;

import pap2023z.z09.database.AccountsEntity;

public class AccountsDTO {
    private int accountId;
    private String email;
    private String password;
    private int type;
    private String name;
    private String surname;

    public AccountsDTO(int accountId, String email, String password, int type, String name, String surname) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.type = type;
        this.name = name;
        this.surname = surname;
    }

    public AccountsDTO() {
    }

    public int getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public static AccountsDTO fromEntity(AccountsEntity entity) {
        return new AccountsDTO(
                entity.getAccountId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getType(),
                entity.getName(),
                entity.getSurname()
        );
    }
}
