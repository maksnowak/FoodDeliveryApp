package pap2023z.z09.accounts;

import pap2023z.z09.database.AccountTypesEntity;

public class AccountTypesDTO {
    private int typeId;
    private String name;

    public AccountTypesDTO(int typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public AccountTypesDTO() {
    }

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void fromEntity(AccountTypesEntity entity) {
        this.typeId = entity.getTypeId();
        this.name = entity.getName();
    }
}
