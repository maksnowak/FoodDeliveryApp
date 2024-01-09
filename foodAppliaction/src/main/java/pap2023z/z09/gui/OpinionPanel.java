package pap2023z.z09.gui;

import pap2023z.z09.accounts.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpinionPanel extends JPanel {
        public OpinionPanel(Callback callback) {
            JLabel upperLabel = new JLabel("Wystaw opinię");
            JTextField opinionField = new JTextField();
            JButton sendButton = new JButton("Wyślij");
            JButton returnButton = new JButton("Powrót");

            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String opinionText = opinionField.getText();

                    // TODO: wysłij opinię do bazy danych
                    // rzeczy
                    // wyślij opinię
                    // FIXME - nie działa XDXDXD

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
            add(upperLabel);
            add(opinionField);

            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new GridLayout(1, 2));
            bottomPanel.add(returnButton);
            bottomPanel.add(sendButton);
            add(bottomPanel);
        }
    }
