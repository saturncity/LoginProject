package lenzj.dev.gui;

import lenzj.dev.Session;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JPanel parentPanel;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JButton logInButton;
    private JButton registerButton;
    private JLabel headerLabel;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JPanel buttonPanel;
    private JPanel centerPanel;
    private JPanel footerPanel;
    private JPanel headerPanel;
    private JPanel passwordPanel;
    private JPanel primaryPanel;
    private JPanel textFieldPanel;
    private JPanel usernamePanel;

    public Login(Session session) {
        /* when clicked, check if login details are valid or show reason why wrong */
        // event listener for when the login button is pushed
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if login details match
                if (session.getDatabase().checkUser(usernameField.getText(), passwordField.getText())) {
                    // set user in the session using method
                    session.setUser(session.getDatabase().getUser(usernameField.getText()));

                    // show home panel using the session's method
                    session.setActivePanel(session.getHome().getParentPanel());
                } else {
                    // show correct error message using JOptionPane
                    // first, check if fields are filled out
                    if (usernameField.getText().equals("") || passwordField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill out all fields.");
                    } else {
                        // then, check if user exists, if it does then incorrect password otherwise user never existed
                        if (session.getDatabase().checkUser(usernameField.getText())) {
                            JOptionPane.showMessageDialog(null, "Incorrect password.");
                        } else {
                            JOptionPane.showMessageDialog(null, "User does not exist.");
                        }
                    }
                }
            }
        });

        /* when clicked, transfer information from the login username field to the register login field (QoL)*/
        // event listener for when the register button is pushed
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // write username & password from login to register
                session.getRegister().getUsernameField().setText(usernameField.getText());
                session.getRegister().getPasswordField().setText(passwordField.getText());

                // show register panel & check conditions again
                session.setActivePanel(session.register.getParentPanel());
                session.getRegister().checkConditions(session.database);
            }
        });
    }

    public JPanel getParentPanel() {
        return parentPanel;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }
}
