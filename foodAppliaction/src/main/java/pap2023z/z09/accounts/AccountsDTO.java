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

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
