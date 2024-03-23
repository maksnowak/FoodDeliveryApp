package pl.foodapp.gui;

import pl.foodapp.reviews.AddReviews;
import pl.foodapp.reviews.ReviewsDAO;
import pl.foodapp.reviews.ReviewsDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpinionPanel extends JPanel {
    private final ReviewsDAO reviewsDAO = new ReviewsDAO();
    int customerId;
    int restaurantId;

        public OpinionPanel(Callback callback) {
            SpinnerModel sm = new SpinnerNumberModel(0, 0, 5, 1);

            JLabel upperLabel = new JLabel("Review restaurant");
            JTextField opinionField = new JTextField();
            JLabel starLabel = new JLabel("Stars:");
            JSpinner starField = new JSpinner(sm);
            JButton sendButton = new JButton("Send");
            JButton returnButton = new JButton("Back");


            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ReviewsDTO review = new ReviewsDTO();

                    review.setDescription(opinionField.getText());
                    review.setStars((Integer) starField.getValue());
                    review.setRestaurantId(restaurantId);
                    review.setCustomer(customerId);

                    AddReviews adder = new AddReviews(reviewsDAO);

                    adder.addReview(review);

                    JOptionPane.showMessageDialog(null, "Opinion sent!");
                    opinionField.setText("");
                    ((App) callback).cardLayout.show(((App) callback).getContentPane(), "MainMenu");

                }
            });

            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    opinionField.setText("");
                    ((App) callback).cardLayout.show(((App) callback).getContentPane(), "OpinionRestaurantChoice");
                }
            });


            setLayout(new GridLayout(3, 1));

            JPanel decisionPanel = new JPanel(new GridLayout(1, 2));
            decisionPanel.add(upperLabel);
            decisionPanel.add(opinionField);
            add(decisionPanel);

            JPanel starPanel = new JPanel();
            starPanel.setLayout(new GridLayout(1, 2));
            starPanel.add(starLabel);
            starPanel.add(starField);
            add(starPanel);

            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new GridLayout(1, 2));
            bottomPanel.add(returnButton);
            bottomPanel.add(sendButton);
            add(bottomPanel);
        }

        public void enter(int cId, int rId) {
            customerId = cId;
            restaurantId = rId;
        }
    }
