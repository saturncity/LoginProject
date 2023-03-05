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
                    boolean austinAvailability = session.getReservationDatabase().checkReservation("Austin", date);
                    boolean kowloonAvailability = session.getReservationDatabase().checkReservation("Kowloon", date);
                    boolean pekingAvailability = session.getReservationDatabase().checkReservation("Peking", date);
                    boolean modyAvailability = session.getReservationDatabase().checkReservation("Mody", date);

                    // if table is taken change button to "RESERVED BY " + name of user & disable button
                    if (!austinAvailability) {
                        session.getTable().getBookAustinButton().setText("RESERVED BY " + session.getReservationDatabase().getReservation("Austin", date).getName());
                        session.getTable().getBookAustinButton().setEnabled(false);
                    } else {
                        session.getTable().getBookAustinButton().setText("Book Austin");
                        session.getTable().getBookAustinButton().setEnabled(true);
                    }

                    if (!kowloonAvailability) {
                        session.getTable().getBookKowloonButton().setText("RESERVED BY " + session.getReservationDatabase().getReservation("Kowloon", date).getName());
                        session.getTable().getBookKowloonButton().setEnabled(false);
                    } else {
                        session.getTable().getBookKowloonButton().setText("Book Kowloon");
                        session.getTable().getBookKowloonButton().setEnabled(true);
                    }

                    if (!pekingAvailability) {
                        session.getTable().getBookPekingButton().setText("RESERVED BY " + session.getReservationDatabase().getReservation("Peking", date).getName());
                        session.getTable().getBookPekingButton().setEnabled(false);
                    } else {
                        session.getTable().getBookPekingButton().setText("Book Peking");
                        session.getTable().getBookPekingButton().setEnabled(true);
                    }

                    if (!modyAvailability) {
                        session.getTable().getBookModyButton().setText("RESERVED BY " + session.getReservationDatabase().getReservation("Mody", date).getName());
                        session.getTable().getBookModyButton().setEnabled(false);
                    } else {
                        session.getTable().getBookModyButton().setText("Book Mody");
                        session.getTable().getBookModyButton().setEnabled(true);
                    }


                    if (!austinAvailability && !kowloonAvailability && !pekingAvailability && !modyAvailability) {
                        session.getTable().getErrorLabel().setText("All tables are taken for this date.");
                    } else {
                        session.getTable().getErrorLabel().setText(" ");
                        session.setActivePanel(session.getTable().getParentPanel());
                    }
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

    public JDateChooser getDateChooser() {
        return dateChooser;
    }
}

