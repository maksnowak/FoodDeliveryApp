package pap2023z.z09.gui;

import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.restaurants.RestaurantsDAO;

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

    private RestaurantsDAO restaurantsDAO = new RestaurantsDAO();

    public ChangeHoursPanel(Callback callback) {

        // Stworznie siatki 5x2
        setLayout(new GridLayout(5, 2));

        // Dodanie pól tekstowych, etykiet i spinnerów
        add(new JLabel("Otwarcie pon-pt:"));
        openWeekdaysSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor openWeekdaysEditor = new JSpinner.DateEditor(openWeekdaysSpinner, "HH:mm");
        openWeekdaysSpinner.setEditor(openWeekdaysEditor);
        add(openWeekdaysSpinner);

        add(new JLabel("Zamknięcie pon-pt:"));
        closeWeekdaysSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor closeWeekdaysEditor = new JSpinner.DateEditor(closeWeekdaysSpinner, "HH:mm");
        closeWeekdaysSpinner.setEditor(closeWeekdaysEditor);
        add(closeWeekdaysSpinner);

        add(new JLabel("Otwarcie weekendy:"));
        openWeekendsSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor openWeekendsEditor = new JSpinner.DateEditor(openWeekendsSpinner, "HH:mm");
        openWeekendsSpinner.setEditor(openWeekendsEditor);
        add(openWeekendsSpinner);

        add(new JLabel("Zamknięcie weekendy:"));
        closeWeekendsSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor closeWeekendsEditor = new JSpinner.DateEditor(closeWeekendsSpinner, "HH:mm");
        closeWeekendsSpinner.setEditor(closeWeekendsEditor);
        add(closeWeekendsSpinner);

        // Dodanie przycisku do zapisywania zmian
        JButton saveButton = new JButton("Zapisz");
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

            JOptionPane.showMessageDialog(this, "Zmieniono godziny otwarcia.");

            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
        });
        add(saveButton);

        // Dodanie przycisku powrotu
        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
        });
        add(backButton);
    }

    public void setRestaurant(RestaurantsEntity restaurant) {
        this.restaurant = restaurant;

        openWeekdaysSpinner.setValue(restaurant.getOpensWeekdays());
        closeWeekdaysSpinner.setValue(restaurant.getClosesWeekdays());
        openWeekendsSpinner.setValue(restaurant.getOpensWeekends());
        closeWeekendsSpinner.setValue(restaurant.getClosesWeekends());
    }
}