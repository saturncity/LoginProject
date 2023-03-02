package lenzj.dev;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    JPanel parentPanel;
    JPasswordField passwordField;
    JTextField usernameField;
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

    public Login(Navigator navigator) {
        
        /* when clicked, check if login details are valid or show reason why wrong */
        // event listener for when the login button is pushed
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if login details match
                if (navigator.database.checkUser(usernameField.getText(), passwordField.getText())) {
                    // set user in the naviator's field
                    navigator.user = navigator.database.getUser(usernameField.getText());

                    // show home panel using the navigator's method
                    navigator.setActivePanel(navigator.home.parentPanel);
                } else {
                    // show correct error message using JOptionPane
                    // first, check if fields are filled out
                    if (usernameField.getText().equals("") || passwordField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill out all fields.");
                    } else {
                        // then, check if user exists, if it does then incorrect password otherwise user never existed
                        if (navigator.database.checkUser(usernameField.getText())) {
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
                navigator.register.usernameField.setText(usernameField.getText());
                navigator.register.passwordField.setText(passwordField.getText());

                // show register panel & check conditions again
                navigator.setActivePanel(navigator.register.parentPanel);
                navigator.register.checkConditions();
            }
        });
    }
}
