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

                // show register panel
                navigator.setActivePanel(navigator.register.parentPanel);
            }
        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
