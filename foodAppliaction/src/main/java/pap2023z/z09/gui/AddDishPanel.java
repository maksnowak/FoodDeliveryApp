package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

import pap2023z.z09.database.RestaurantsEntity;
import pap2023z.z09.dishes.*;

public class AddDishPanel extends JPanel {
    private JTextField nameField;
    private JComboBox<String> typeComboBox;
    private JCheckBox vegetarianCheckBox;
    private JTextField priceField;
    private JTextField kcalField;
    private JButton addButton;
    private AddDish addDish;

    public AddDishPanel(AddDish addDish, Callback callback) {
        this.addDish = addDish;

        // Stworzenie siatki 6x2
        setLayout(new GridLayout(6, 2));

        // Dodanie pól tekstowych i etykiet
        add(new JLabel("Nazwa:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Typ dania:"));
        typeComboBox = new JComboBox<>();
        typeComboBox.addItem("Przystawka");
        typeComboBox.addItem("Zupa");
        typeComboBox.addItem("Danie główne");
        typeComboBox.addItem("Deser");
        typeComboBox.addItem("Dodatki");
        typeComboBox.addItem("Sałatki");
        typeComboBox.addItem("Napoje");
        add(typeComboBox);

        add(new JLabel("Wegetariańskie:"));
        vegetarianCheckBox = new JCheckBox();
        add(vegetarianCheckBox);

        add(new JLabel("Cena:"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Kcal:"));
        kcalField = new JTextField();
        add(kcalField);

        // Dodanie przycisku powrotu
        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e2 -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");
        });
        add(backButton);

        // Dodanie przycisku dodawania dania
        addButton = new JButton("Dodaj danie");
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            int typeId = typeComboBox.getSelectedIndex() + 1;
            boolean vegetarian = vegetarianCheckBox.isSelected();
            BigDecimal price = new BigDecimal(priceField.getText());
            BigDecimal kcal = new BigDecimal(kcalField.getText());

            DishesDTO dish = new DishesDTO();
            dish.setName(name);
            dish.setTypeId(typeId);
            dish.setVegetarian(vegetarian);
            dish.setPrice(price);
            dish.setKcal(kcal);

            RestaurantsEntity restaurant = ((App) callback).selectedRestaurant;
            dish.setRestaurantId(restaurant.getRestaurantId());
            int dishId = addDish.addDish(dish);
            dish.setDishId(dishId);

            // Wyświetlenie komunikatu o dodaniu dania i przejście do panelu modyfikacji restauracji
            JOptionPane.showMessageDialog(this, "Dodano danie.");
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurantDetails");


        });
        add(addButton);
    }
}