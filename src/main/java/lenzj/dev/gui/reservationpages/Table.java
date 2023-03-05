package lenzj.dev.gui.reservationpages;

import lenzj.dev.Session;
import lenzj.dev.objects.ReservationEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table {
    private JPanel parentPanel;
    private JPanel primaryPanel;
    private JPanel footerPanel;
    private JPanel bottomPanel;
    private JButton goBackButton;
    private JButton confirmButton;
    private JPanel detailsPanel;
    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JLabel dateDynamicLabel;
    private JLabel dateLabel;
    private JTextField nameField;
    private JTextField phoneField;
    private JPanel austinPanel;
    private JPanel kowloonPanel;
    private JPanel modyPanel;
    private JPanel pekingPanel;
    private JLabel tableLabel;
    private JLabel tableDynamicLabel;
    private JButton bookAustinButton;
    private JButton bookKowloonButton;
    private JButton bookPekingButton;
    private JButton bookModyButton;
    private JPanel austinPrimaryPanel;
    private JPanel austinTable;
    private JPanel austinSeat1;
    private JPanel austinSeat2;
    private JPanel austinSeat3;
    private JPanel austinSeat4;
    private JPanel kowloonPrimaryPanel;
    private JPanel kowloonTable;
    private JPanel kowloonSeat1;
    private JPanel kowloonSeat2;
    private JPanel modyPrimaryPanel;
    private JPanel modyTable;
    private JPanel modySeat1;
    private JPanel modySeat2;
    private JPanel modySeat4;
    private JPanel pekingPrimaryPanel;
    private JPanel pekingTable;
    private JPanel pekingSeat1;
    private JPanel pekingSeat2;
    private JPanel pekingSeat3;
    private JPanel pekingSeat4;
    private JPanel pekingSeat5;
    private JPanel pekingSeat6;
    private JLabel austinLabel;
    private JLabel kowloonLabel;
    private JLabel modyLabel;
    private JLabel pekingLabel;
    private JLabel errorLabel;

    public Table(Session session) {
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                session.getReservation().getNameField().setText(nameField.getText());
                session.getReservation().getPhoneField().setText(phoneField.getText());
                session.setActivePanel(session.getReservation().getParentPanel());
            }
        });
        bookAustinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableDynamicLabel.setText("Austin");
            }
        });
        bookKowloonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableDynamicLabel.setText("Kowloon");
            }
        });
        bookPekingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableDynamicLabel.setText("Peking");
            }
        });
        bookModyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableDynamicLabel.setText("Mody");
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check that all fields are filled out
                String name = nameField.getText();
                String phone = phoneField.getText();
                String date = dateDynamicLabel.getText();
                String table = tableDynamicLabel.getText();

                if (name.equals("") || phone.equals("") || date.equals("No date selected") || table.equals("No table selected")) {
                    errorLabel.setText("Please fill out all fields.");
                } else {
                    ReservationEvent reservation = new ReservationEvent(name, phone, date, table);
                    session.getReservationDatabase().addReservation(reservation);
                }
            }
        });
    }

    public JPanel getParentPanel() {
        return parentPanel;
    }

    private void createUIComponents() {
        dateDynamicLabel = new JLabel();
        dateDynamicLabel.setText("No date selected");

        tableDynamicLabel = new JLabel();
        tableDynamicLabel.setText("No table selected");

        errorLabel = new JLabel();
        errorLabel.setText(" ");
        errorLabel.setForeground(Color.RED);

    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JLabel getDateDynamicLabel() {
        return dateDynamicLabel;
    }

    public JPanel getPrimaryPanel() {
        return primaryPanel;
    }

    public JPanel getFooterPanel() {
        return footerPanel;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JPanel getDetailsPanel() {
        return detailsPanel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getPhoneLabel() {
        return phoneLabel;
    }

    public JLabel getDateLabel() {
        return dateLabel;
    }

    public JPanel getAustinPanel() {
        return austinPanel;
    }

    public JPanel getKowloonPanel() {
        return kowloonPanel;
    }

    public JPanel getModyPanel() {
        return modyPanel;
    }

    public JPanel getPekingPanel() {
        return pekingPanel;
    }

    public JLabel getTableLabel() {
        return tableLabel;
    }

    public JLabel getTableDynamicLabel() {
        return tableDynamicLabel;
    }

    public JButton getBookAustinButton() {
        return bookAustinButton;
    }

    public JButton getBookKowloonButton() {
        return bookKowloonButton;
    }

    public JButton getBookPekingButton() {
        return bookPekingButton;
    }

    public JButton getBookModyButton() {
        return bookModyButton;
    }

    public JPanel getAustinPrimaryPanel() {
        return austinPrimaryPanel;
    }

    public JPanel getAustinTable() {
        return austinTable;
    }

    public JPanel getAustinSeat1() {
        return austinSeat1;
    }

    public JPanel getAustinSeat2() {
        return austinSeat2;
    }

    public JPanel getAustinSeat3() {
        return austinSeat3;
    }

    public JPanel getAustinSeat4() {
        return austinSeat4;
    }

    public JPanel getKowloonPrimaryPanel() {
        return kowloonPrimaryPanel;
    }

    public JPanel getKowloonTable() {
        return kowloonTable;
    }

    public JPanel getKowloonSeat1() {
        return kowloonSeat1;
    }

    public JPanel getKowloonSeat2() {
        return kowloonSeat2;
    }

    public JPanel getModyPrimaryPanel() {
        return modyPrimaryPanel;
    }

    public JPanel getModyTable() {
        return modyTable;
    }

    public JPanel getModySeat1() {
        return modySeat1;
    }

    public JPanel getModySeat2() {
        return modySeat2;
    }

    public JPanel getModySeat4() {
        return modySeat4;
    }

    public JPanel getPekingPrimaryPanel() {
        return pekingPrimaryPanel;
    }

    public JPanel getPekingTable() {
        return pekingTable;
    }

    public JPanel getPekingSeat1() {
        return pekingSeat1;
    }

    public JPanel getPekingSeat2() {
        return pekingSeat2;
    }

    public JPanel getPekingSeat3() {
        return pekingSeat3;
    }

    public JPanel getPekingSeat4() {
        return pekingSeat4;
    }

    public JPanel getPekingSeat5() {
        return pekingSeat5;
    }

    public JPanel getPekingSeat6() {
        return pekingSeat6;
    }

    public JLabel getAustinLabel() {
        return austinLabel;
    }

    public JLabel getKowloonLabel() {
        return kowloonLabel;
    }

    public JLabel getModyLabel() {
        return modyLabel;
    }

    public JLabel getPekingLabel() {
        return pekingLabel;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }
}
