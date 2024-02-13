package pl.foodapp.restaurants;

import pl.foodapp.database.RestaurantsEntity;
import java.sql.Time;

public class AddRestaurant {
    private final RestaurantsDAO restaurantsDAO;

    public AddRestaurant(RestaurantsDAO restaurantsDAO) {
        this.restaurantsDAO = restaurantsDAO;
    }

    public int addRestaurant(RestaurantsDTO restaurant) throws InvalidTimeException{
        verifyWeekdays(restaurant);
        verifyWeekends(restaurant);
        RestaurantsEntity entity = new RestaurantsEntity();
        entity.setName(restaurant.getName());
        entity.setOpensWeekdays(restaurant.getOpensWeekdays());
        entity.setClosesWeekdays(restaurant.getClosesWeekdays());
        entity.setOpensWeekends(restaurant.getOpensWeekends());
        entity.setClosesWeekends(restaurant.getClosesWeekends());
        return restaurantsDAO.addRestaurant(entity);
    }

    public void verifyWeekdays(RestaurantsDTO restaurant) throws InvalidTimeException {
        if (!(restaurant.getClosesWeekdays().after(restaurant.getOpensWeekdays()))) {
            throw new InvalidTimeException("Weekdays closing time cannot be before opening time");
        }
    }

    public void verifyWeekends(RestaurantsDTO restaurant) throws InvalidTimeException {
        if (!(restaurant.getClosesWeekends().after(restaurant.getOpensWeekends()))) {
            throw new InvalidTimeException("Weekdays closing time cannot be before opening time");
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
