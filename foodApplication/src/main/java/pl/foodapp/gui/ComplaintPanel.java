package pl.foodapp.gui;

import pl.foodapp.complaints.AddComplaint;
import pl.foodapp.complaints.ComplaintsDAO;
import pl.foodapp.complaints.ComplaintsDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComplaintPanel extends JPanel {
    private final ComplaintsDAO reviewsDAO = new ComplaintsDAO();
    int customerId;
    int orderId;

        public ComplaintPanel(Callback callback) {
            setLayout(new BorderLayout());
            JLabel upperLabel = new JLabel("Place a complaint:");
            JTextField complaintField = new JTextField();
            JButton sendButton = new JButton("Send");
            JButton returnButton = new JButton("Back");

            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ComplaintsDTO complaint = new ComplaintsDTO();

                    complaint.setDescription(complaintField.getText());
                    complaint.setOrderId(orderId);
                    complaint.setOpen(true);

                    AddComplaint adder = new AddComplaint(reviewsDAO);

                    adder.addComplaint(complaint);

                    JOptionPane.showMessageDialog(null, "Complaint sent!");
                    complaintField.setText("");
                    callback.enterHistoryPanel();
                }
            });

            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    complaintField.setText("");
                    callback.enterHistoryPanel();
                }
            });

            add(upperLabel, BorderLayout.NORTH);

            add(complaintField, BorderLayout.CENTER);

            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new GridLayout(1, 2));
            bottomPanel.add(returnButton);
            bottomPanel.add(sendButton);
            add(bottomPanel, BorderLayout.SOUTH);
        }

        public void enter(int cId, int rId) {
            customerId = cId;
            orderId = rId;
        }
    }
