package lenzj.dev.gui.reservationpages;

import com.toedter.calendar.JDateChooser;
import lenzj.dev.Session;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class Reservation {
    public Reservation(Session session) {

        exitAppButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                session.setActivePanel(session.getHome().getParentPanel());
            }
        });

        tableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check that all fields are filled out
                String name = nameField.getText();
                String phone = phoneField.getText();

                if (name.equals("") || phone.equals("") || dateChooser.getDate() == null) {
                    errorLabel.setText("Please fill out all fields.");
                } else {
                    String date = dateChooser.getDate().getDate() + "/" + (dateChooser.getDate().getMonth()+1) + "/" + (dateChooser.getDate().getYear()+1900);
                    session.getTable().getNameField().setText(name);
                    session.getTable().getPhoneField().setText(phone);
                    session.getTable().getDateDynamicLabel().setText(date);

                    // check if table is taken for selected date
                    session.getTable().getBookAustinButton().setEnabled(session.getReservationDatabase().checkReservation("Austin", date));
                    session.getTable().getBookKowloonButton().setEnabled(session.getReservationDatabase().checkReservation("Kowloon", date));
                    session.getTable().getBookPekingButton().setEnabled(session.getReservationDatabase().checkReservation("Peking", date));
                    session.getTable().getBookModyButton().setEnabled(session.getReservationDatabase().checkReservation("Mody", date));

                    session.setActivePanel(session.getTable().getParentPanel());
                }
            }
        });
    }


    private JPanel parentPanel;
    private JPanel headerPanel;
    private JPanel primaryPanel;
    private JPanel footerPanel;
    private JPanel centerPanel;
    private JButton exitAppButton;
    private JTextField nameField;
    private JTextField phoneField;
    private JDateChooser dateChooser;
    private JButton tableButton;
    private JLabel dateLabel;
    private JLabel phoneLabel;
    private JLabel nameLabel;
    private JLabel errorLabel;


    public JPanel getParentPanel() {
        return parentPanel;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    private void createUIComponents() {
        dateChooser = new JDateChooser();
        errorLabel = new JLabel();
        errorLabel.setText(" ");
        errorLabel.setForeground(java.awt.Color.RED);
    }


}

