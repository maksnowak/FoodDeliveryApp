package pl.foodapp.dishTypes;

import pl.foodapp.database.DishTypesEntity;


public class DishTypesDTO {
    private int typeId;
    private String name;

    public DishTypesDTO(int typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public DishTypesDTO() {
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

    public static DishTypesDTO fromEntity(DishTypesEntity entity) {
        return new DishTypesDTO(
                entity.getTypeId(),
                entity.getName()
        );
    }



}
