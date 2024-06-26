package pl.foodapp.gui;

import pl.foodapp.database.RestaurantsEntity;
import pl.foodapp.restaurants.RestaurantsDAO;

import javax.swing.*;
import java.awt.*;

public class ChangeHoursPanel extends JPanel {
    private JSpinner openWeekdaysSpinner;
    private JSpinner closeWeekdaysSpinner;
    private JSpinner openWeekendsSpinner;
    private JSpinner closeWeekendsSpinner;
    private RestaurantsEntity restaurant;

    private RestaurantsDAO restaurantsDAO = new RestaurantsDAO();

    public ChangeHoursPanel(Callback callback) {

        // Create a 5x2 grid
        setLayout(new GridLayout(5, 2));

        // Add text fields, labels and spinners
        add(new JLabel("Opening time mon-fri:"));
        openWeekdaysSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor openWeekdaysEditor = new JSpinner.DateEditor(openWeekdaysSpinner, "HH:mm");
        openWeekdaysSpinner.setEditor(openWeekdaysEditor);
        add(openWeekdaysSpinner);

        add(new JLabel("Closing time mon-fri:"));
        closeWeekdaysSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor closeWeekdaysEditor = new JSpinner.DateEditor(closeWeekdaysSpinner, "HH:mm");
        closeWeekdaysSpinner.setEditor(closeWeekdaysEditor);
        add(closeWeekdaysSpinner);

        add(new JLabel("Opening time weekends:"));
        openWeekendsSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor openWeekendsEditor = new JSpinner.DateEditor(openWeekendsSpinner, "HH:mm");
        openWeekendsSpinner.setEditor(openWeekendsEditor);
        add(openWeekendsSpinner);

        add(new JLabel("Closing time weekends:"));
        closeWeekendsSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor closeWeekendsEditor = new JSpinner.DateEditor(closeWeekendsSpinner, "HH:mm");
        closeWeekendsSpinner.setEditor(closeWeekendsEditor);
        add(closeWeekendsSpinner);

        // Add a button to save changes
        JButton saveButton = new JButton("Save changes");
        saveButton.addActionListener(e -> {
            java.util.Date openWeekdaysDate = (java.util.Date) openWeekdaysSpinner.getValue();
            java.sql.Time openWeekdaysTime = new java.sql.Time(openWeekdaysDate.getTime());
            restaurant.setOpensWeekdays(openWeekdaysTime);

            java.util.Date closeWeekdaysDate = (java.util.Date) closeWeekdaysSpinner.getValue();
            java.sql.Time closeWeekdaysTime = new java.sql.Time(closeWeekdaysDate.getTime());
            restaurant.setClosesWeekdays(closeWeekdaysTime);

            java.util.Date openWeekendsDate = (java.util.Date) openWeekendsSpinner.getValue();
            java.sql.Time openWeekendsTime = new java.sql.Time(openWeekendsDate.getTime());
            restaurant.setOpensWeekends(openWeekendsTime);

            java.util.Date closeWeekendsDate = (java.util.Date) closeWeekendsSpinner.getValue();
            java.sql.Time closeWeekendsTime = new java.sql.Time(closeWeekendsDate.getTime());
            restaurant.setClosesWeekends(closeWeekendsTime);

            restaurantsDAO.updateRestaurant(restaurant);

            JOptionPane.showMessageDialog(this, "Changes saved");

            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
        });


        // Add a back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
        });
        add(backButton);
        add(saveButton);
    }

    public void setRestaurant(RestaurantsEntity restaurant) {
        this.restaurant = restaurant;

        openWeekdaysSpinner.setValue(restaurant.getOpensWeekdays());
        closeWeekdaysSpinner.setValue(restaurant.getClosesWeekdays());
        openWeekendsSpinner.setValue(restaurant.getOpensWeekends());
        closeWeekendsSpinner.setValue(restaurant.getClosesWeekends());
    }
}