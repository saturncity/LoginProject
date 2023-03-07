package lenzj.dev.gui;

import lenzj.dev.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

public class Login {
    private JPanel parentPanel;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JButton logInButton;
    private JButton registerButton;
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
    private JLabel footerLabel;
    private JPanel forgotPanel;
    private JLabel forgotUsernameHLabel;
    private JLabel forgotPasswordHLabel;
    private JLabel errorLabel;
    private Session session;

    public Login(Session session) {
        this.session = session;
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

                    // clear passwords
                    passwordField.setText("");
                } else {
                    // show correct error message using JOptionPane
                    // first, check if fields are filled out
                    if (usernameField.getText().equals("") || passwordField.getText().equals("")) {
                        errorLabel.setText("Please fill out all fields.");
                    } else {
                        // then, check if user exists, if it does then incorrect password otherwise user never existed
                        if (session.getDatabase().checkUser(usernameField.getText())) {
                            errorLabel.setText("Incorrect password.");
                        } else {
                            errorLabel.setText("User does not exist.");
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

    private void createUIComponents() {
        final Color linkColor = new Color(6,69,173);

        forgotUsernameHLabel = new JLabel("Forgot Username?");
        forgotPasswordHLabel = new JLabel("Forgot Password?");

        forgotUsernameHLabel.setForeground(linkColor);
        forgotPasswordHLabel.setForeground(linkColor);

        Font font = forgotUsernameHLabel.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        forgotUsernameHLabel.setFont(font.deriveFont(attributes));
        forgotPasswordHLabel.setFont(font.deriveFont(attributes));

        forgotUsernameHLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgotPasswordHLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        forgotUsernameHLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                session.setActivePanel(session.getForgotUsername().getParentPanel());
            }
        });

        forgotPasswordHLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                session.getForgotPassword().getUsernameField().setText(usernameField.getText());
                session.setActivePanel(session.getForgotPassword().getParentPanel());
            }
        });

        errorLabel = new JLabel();
        errorLabel.setText(" ");
        errorLabel.setForeground(Color.RED);

    }
}
