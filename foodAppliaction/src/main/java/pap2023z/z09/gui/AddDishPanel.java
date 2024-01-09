package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

import pap2023z.z09.dishes.*;

public class AddDishPanel extends JPanel {
    private JTextField nameField;
    private JTextField typeIdField;
    private JCheckBox vegetarianCheckBox;
    private JTextField priceField;
    private JTextField kcalField;
    private JButton addButton;
    private AddDish addDish;

    public AddDishPanel(AddDish addDish, Callback callback) {
        this.addDish = addDish;

        setLayout(new GridLayout(6, 2));

        add(new JLabel("Nazwa:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Typ dania:"));
        typeIdField = new JTextField();
        add(typeIdField);

        add(new JLabel("Wegetariańskie:"));
        vegetarianCheckBox = new JCheckBox();
        add(vegetarianCheckBox);

        add(new JLabel("Cena:"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Kcal:"));
        kcalField = new JTextField();
        add(kcalField);

        JButton backButton = new JButton("Powrót");
        backButton.addActionListener(e2 -> {
            ((App) callback).cardLayout.show(((App) callback).getContentPane(), "ModifyRestaurants");
        });
        add(backButton);

        addButton = new JButton("Dodaj danie");
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            int typeId = Integer.parseInt(typeIdField.getText());
            boolean vegetarian = vegetarianCheckBox.isSelected();
            BigDecimal price = new BigDecimal(priceField.getText());
            BigDecimal kcal = new BigDecimal(kcalField.getText());

            DishesDTO dish = new DishesDTO();
            dish.setName(name);
            dish.setTypeId(typeId);
            dish.setVegetarian(vegetarian);
            dish.setPrice(price);
            dish.setKcal(kcal);

            addDish.addDish(dish);


        });
        add(addButton);
    }
}