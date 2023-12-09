package pap2023z.z09.restaurants;

import pap2023z.z09.database.RestaurantsEntity;

import java.sql.Time;

public class RestaurantsDTO {
    private int restaurantId;
    private String name;
    private Time opensWeekdays;
    private Time closesWeekdays;
    private Time opensWeekends;
    private Time closesWeekends;

    public RestaurantsDTO(int restaurantId, String name, Time opensWeekdays, Time closesWeekdays, Time opensWeekends, Time closesWeekends) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.opensWeekdays = opensWeekdays;
        this.closesWeekdays = closesWeekdays;
        this.opensWeekends = opensWeekends;
        this.closesWeekends = closesWeekends;
    }

    public RestaurantsDTO() {
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public Time getOpensWeekdays() {
        return opensWeekdays;
    }

    public Time getClosesWeekdays() {
        return closesWeekdays;
    }

    public Time getOpensWeekends() {
        return opensWeekends;
    }

    public Time getClosesWeekends() {
        return closesWeekends;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpensWeekdays(Time opensWeekdays) {
        this.opensWeekdays = opensWeekdays;
    }

    public void setClosesWeekdays(Time closesWeekdays) {
        this.closesWeekdays = closesWeekdays;
    }

    public void setOpensWeekends(Time opensWeekends) {
        this.opensWeekends = opensWeekends;
    }

    public void setClosesWeekends(Time closesWeekends) {
        this.closesWeekends = closesWeekends;
    }

    public static RestaurantsDTO fromEntity(RestaurantsEntity entity) {
        return new RestaurantsDTO(
                entity.getRestaurantId(),
                entity.getName(),
                entity.getOpensWeekdays(),
                entity.getClosesWeekdays(),
                entity.getOpensWeekends(),
                entity.getClosesWeekends()
        );
    }
}
