package pap2023z.z09.gui;

import pap2023z.z09.database.RestaurantsEntity;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.Date;
import java.util.Calendar;

public class ChangeHoursPanel extends JPanel {
    private JSpinner openWeekdaysSpinner;
    private JSpinner closeWeekdaysSpinner;
    private JSpinner openWeekendsSpinner;
    private JSpinner closeWeekendsSpinner;
    private RestaurantsEntity restaurant;

    public ChangeHoursPanel(Callback callback) {
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Open Weekdays:"));
        openWeekdaysSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor openWeekdaysEditor = new JSpinner.DateEditor(openWeekdaysSpinner, "HH:mm");
        openWeekdaysSpinner.setEditor(openWeekdaysEditor);
        add(openWeekdaysSpinner);

        add(new JLabel("Close Weekdays:"));
        closeWeekdaysSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor closeWeekdaysEditor = new JSpinner.DateEditor(closeWeekdaysSpinner, "HH:mm");
        closeWeekdaysSpinner.setEditor(closeWeekdaysEditor);
        add(closeWeekdaysSpinner);

        add(new JLabel("Open Weekends:"));
        openWeekendsSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor openWeekendsEditor = new JSpinner.DateEditor(openWeekendsSpinner, "HH:mm");
        openWeekendsSpinner.setEditor(openWeekendsEditor);
        add(openWeekendsSpinner);

        add(new JLabel("Close Weekends:"));
        closeWeekendsSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor closeWeekendsEditor = new JSpinner.DateEditor(closeWeekendsSpinner, "HH:mm");
        closeWeekendsSpinner.setEditor(closeWeekendsEditor);
        add(closeWeekendsSpinner);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            // Save the new hours to the restaurant object
            restaurant.setOpensWeekdays((Time) openWeekdaysSpinner.getValue());
            restaurant.setClosesWeekdays((Time) closeWeekdaysSpinner.getValue());
            restaurant.setOpensWeekends((Time) openWeekendsSpinner.getValue());
            restaurant.setClosesWeekends((Time) closeWeekendsSpinner.getValue());

            // Go back to the ModifyRestaurantDetailsPanel
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
        });
        add(saveButton);
    }

    public void setRestaurant(RestaurantsEntity restaurant) {
        this.restaurant = restaurant;

        // Set the spinners' values to the current hours
        openWeekdaysSpinner.setValue(restaurant.getOpensWeekdays());
        closeWeekdaysSpinner.setValue(restaurant.getClosesWeekdays());
        openWeekendsSpinner.setValue(restaurant.getOpensWeekends());
        closeWeekendsSpinner.setValue(restaurant.getClosesWeekends());
    }
}