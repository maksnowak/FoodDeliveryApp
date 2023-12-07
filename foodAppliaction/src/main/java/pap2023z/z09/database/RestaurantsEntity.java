package pap2023z.z09.database;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "restaurants", schema = "public", catalog = "postgres")
public class RestaurantsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;
    @Basic
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @Basic
    @Column(name = "opens_weekdays", nullable = true)
    private Time opensWeekdays;
    @Basic
    @Column(name = "closes_weekdays", nullable = true)
    private Time closesWeekdays;
    @Basic
    @Column(name = "opens_weekends", nullable = true)
    private Time opensWeekends;
    @Basic
    @Column(name = "closes_weekends", nullable = true)
    private Time closesWeekends;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getOpensWeekdays() {
        return opensWeekdays;
    }

    public void setOpensWeekdays(Time opensWeekdays) {
        this.opensWeekdays = opensWeekdays;
    }

    public Time getClosesWeekdays() {
        return closesWeekdays;
    }

    public void setClosesWeekdays(Time closesWeekdays) {
        this.closesWeekdays = closesWeekdays;
    }

    public Time getOpensWeekends() {
        return opensWeekends;
    }

    public void setOpensWeekends(Time opensWeekends) {
        this.opensWeekends = opensWeekends;
    }

    public Time getClosesWeekends() {
        return closesWeekends;
    }

    public void setClosesWeekends(Time closesWeekends) {
        this.closesWeekends = closesWeekends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantsEntity that = (RestaurantsEntity) o;
        return restaurantId == that.restaurantId && Objects.equals(name, that.name) && Objects.equals(opensWeekdays, that.opensWeekdays) && Objects.equals(closesWeekdays, that.closesWeekdays) && Objects.equals(opensWeekends, that.opensWeekends) && Objects.equals(closesWeekends, that.closesWeekends);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, name, opensWeekdays, closesWeekdays, opensWeekends, closesWeekends);
    }
}
