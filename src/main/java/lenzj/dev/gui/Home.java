package lenzj.dev.gui;

import lenzj.dev.Session;
import lenzj.dev.objects.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    private JPanel parentPanel;
    private JPanel headerPanel;
    private JPanel primarypanel;
    private JPanel footerPanel;
    private JPanel centerPanel;
    private JButton signOutButton;
    private JButton createReservationButton;
    private JLabel headerLabel;
    private User user;

    public Home(Session session) {

        createReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                session.getReservation().getNameField().setText(session.getUser().getUsername());
                session.getReservation().getPhoneField().setText(session.getUser().getPhone());
                session.setActivePanel(session.getReservation().getParentPanel());
            }
        });
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                session.setUser(null);
                session.setActivePanel(session.getLogin().getParentPanel());
            }
        });
    }

    public JPanel getParentPanel() {
        return parentPanel;
    }

    private void createUIComponents() {
    }

}
