package lenzj.dev;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    JPanel parentPanel;
    private JPanel headerPanel;
    private JPanel footerPanel;
    private JPanel primaryPanel;
    private JLabel headerLabel;
    private JPanel centerPanel;
    private JPanel textFieldPanel;
    JTextField usernameField;
    private JPanel usernamePanel;
    private JPanel passwordPanel;
    JPasswordField passwordField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPanel buttonPanel;
    private JButton logInButton;
    private JButton registerButton;

    public Login(Navigator navigator) {
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
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (navigator.database.checkUser(usernameField.getText(), passwordField.getText())) {
                    // set user
                    navigator.user = navigator.database.getUser(usernameField.getText());

                    // show home panel
                    navigator.setActivePanel(navigator.home.parentPanel);
                } else {
                    // show correct error message
                    if (usernameField.getText().equals("") || passwordField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill out all fields.");
                    } else {
                        if (navigator.database.checkUser(usernameField.getText())) {
                            JOptionPane.showMessageDialog(null, "Incorrect password.");
                        } else {
                            JOptionPane.showMessageDialog(null, "User does not exist.");
                        }
                    }
                }
            }
        });
    }
}
