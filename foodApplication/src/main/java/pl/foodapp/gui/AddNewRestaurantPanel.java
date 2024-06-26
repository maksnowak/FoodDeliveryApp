package pl.foodapp.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import pl.foodapp.workers.AddWorkerService;
import pl.foodapp.workers.WorkersDAO;
import pl.foodapp.workers.WorkersDTO;
import pl.foodapp.restaurants.AddRestaurant;
import pl.foodapp.restaurants.InvalidTimeException;
import pl.foodapp.restaurants.RestaurantsDTO;

public class AddNewRestaurantPanel extends JPanel {
    private JTextField nameField;
    private JSpinner opensWeekdaysSpinner;
    private JSpinner closesWeekdaysSpinner;
    private JSpinner opensWeekendsSpinner;
    private JSpinner closesWeekendsSpinner;
    private JButton addButton;
    private JButton backButton;
    private AddRestaurant addRestaurant;

    public AddNewRestaurantPanel(AddRestaurant addRestaurant, Callback callback) {
        this.addRestaurant = addRestaurant;

        setLayout(new GridLayout(6, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        Date initialTime = calendar.getTime();

        add(new JLabel("Opening time mon-fri:"));
        opensWeekdaysSpinner = new JSpinner(new SpinnerDateModel(initialTime, null, null, Calendar.HOUR_OF_DAY));
        JSpinner.DateEditor opensWeekdaysEditor = new JSpinner.DateEditor(opensWeekdaysSpinner, "HH:mm");
        opensWeekdaysSpinner.setEditor(opensWeekdaysEditor);
        add(opensWeekdaysSpinner);

        add(new JLabel("Closing time mon-fri:"));
        closesWeekdaysSpinner = new JSpinner(new SpinnerDateModel(initialTime, null, null, Calendar.HOUR_OF_DAY));
        JSpinner.DateEditor closesWeekdaysEditor = new JSpinner.DateEditor(closesWeekdaysSpinner, "HH:mm");
        closesWeekdaysSpinner.setEditor(closesWeekdaysEditor);
        add(closesWeekdaysSpinner);

        add(new JLabel("Opening time weekends:"));
        opensWeekendsSpinner = new JSpinner(new SpinnerDateModel(initialTime, null, null, Calendar.HOUR_OF_DAY));
        JSpinner.DateEditor opensWeekendsEditor = new JSpinner.DateEditor(opensWeekendsSpinner, "HH:mm");
        opensWeekendsSpinner.setEditor(opensWeekendsEditor);
        add(opensWeekendsSpinner);

        add(new JLabel("Closing time weekends:"));
        closesWeekendsSpinner = new JSpinner(new SpinnerDateModel(initialTime, null, null, Calendar.HOUR_OF_DAY));
        JSpinner.DateEditor closesWeekendsEditor = new JSpinner.DateEditor(closesWeekendsSpinner, "HH:mm");
        closesWeekendsSpinner.setEditor(closesWeekendsEditor);
        add(closesWeekendsSpinner);

        addButton = new JButton("Add restaurant");
        addButton.addActionListener(e -> {
            String name = nameField.getText().isEmpty() ? null : nameField.getText();
            if (name == null) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Time opensWeekdays = Time.valueOf(((JSpinner.DateEditor) opensWeekdaysSpinner.getEditor()).getFormat().format(opensWeekdaysSpinner.getValue()) + ":00");
            Time closesWeekdays = Time.valueOf(((JSpinner.DateEditor) closesWeekdaysSpinner.getEditor()).getFormat().format(closesWeekdaysSpinner.getValue()) + ":00");
            Time opensWeekends = Time.valueOf(((JSpinner.DateEditor) opensWeekendsSpinner.getEditor()).getFormat().format(opensWeekendsSpinner.getValue()) + ":00");
            Time closesWeekends = Time.valueOf(((JSpinner.DateEditor) closesWeekendsSpinner.getEditor()).getFormat().format(closesWeekendsSpinner.getValue()) + ":00");

            RestaurantsDTO restaurant = new RestaurantsDTO();
            restaurant.setName(name);
            restaurant.setOpensWeekdays(opensWeekdays);
            restaurant.setClosesWeekdays(closesWeekdays);
            restaurant.setOpensWeekends(opensWeekends);
            restaurant.setClosesWeekends(closesWeekends);

            int restaurantId = 0;
            try {
                restaurantId = addRestaurant.addRestaurant(restaurant);
                restaurant.setRestaurantId(restaurantId);
                AddWorkerService addWorkerService = new AddWorkerService(new WorkersDAO());
                WorkersDTO worker = new WorkersDTO();
                worker.setRestaurantId(restaurantId);
                worker.setWorker(((App) callback).loggedAccount.getAccountId());
                addWorkerService.addWorker(worker);
                JOptionPane.showMessageDialog(this, "Restaurant added.");
                // Refresh the restaurant list in ModifyRestaurantsPanel
                ((App) callback).modifyRestaurantsPanel.refreshRestaurantList();
                ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurants");
            } catch (InvalidTimeException ex) {
                if (ex.getMessage().equals("Weekdays closing time cannot be before opening time")) {
                    JOptionPane.showMessageDialog(this, "Closing time cannot be earlier than opening time", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    throw new RuntimeException(ex);
                }
            }
            restaurant.setRestaurantId(restaurantId);
        });


        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurants");
        });
        add(backButton);
        add(addButton);
    }
}