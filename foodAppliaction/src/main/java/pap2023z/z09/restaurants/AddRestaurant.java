package pap2023z.z09.restaurants;

import pap2023z.z09.database.RestaurantsEntity;
import java.sql.Time;

public class AddRestaurant {
    private final RestaurantsDAO restaurantsDAO;

    public AddRestaurant(RestaurantsDAO restaurantsDAO) {
        this.restaurantsDAO = restaurantsDAO;
    }

    public void addRestaurant(RestaurantsDTO restaurant) throws InvalidTimeException{
        verifyWeekdays(restaurant);
        verifyWeekends(restaurant);
        RestaurantsEntity entity = new RestaurantsEntity();
        entity.setName(restaurant.getName());
        entity.setOpensWeekdays(restaurant.getOpensWeekdays());
        entity.setClosesWeekdays(restaurant.getClosesWeekdays());
        entity.setOpensWeekends(restaurant.getOpensWeekends());
        entity.setClosesWeekends(restaurant.getClosesWeekends());
        restaurantsDAO.addRestaurant(entity);
    }

    public void verifyWeekdays(RestaurantsDTO restaurant) throws InvalidTimeException {
        if (restaurant.getOpensWeekdays().after(restaurant.getClosesWeekdays())) {
            throw new InvalidTimeException("Weekdays opening time cannot be after closing time");
        }
    }

    public void verifyWeekends(RestaurantsDTO restaurant) throws InvalidTimeException {
        if (restaurant.getOpensWeekends().after(restaurant.getClosesWeekends())) {
            throw new InvalidTimeException("Weekends opening time cannot be after closing time");
        }
    }

    public void validateInput(String name, Time opensWeekdays, Time closesWeekdays, Time opensWeekends, Time closesWeekends) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (opensWeekdays == null) {
            throw new IllegalArgumentException("Weekdays opening time cannot be empty");
        }
        if (closesWeekdays == null) {
            throw new IllegalArgumentException("Weekdays closing time cannot be empty");
        }
        if (opensWeekends == null) {
            throw new IllegalArgumentException("Weekends opening time cannot be empty");
        }
        if (closesWeekends == null) {
            throw new IllegalArgumentException("Weekends closing time cannot be empty");
        }
    }
}
