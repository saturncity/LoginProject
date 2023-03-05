package lenzj.dev.gui.forgotpages;

import lenzj.dev.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePassword {
    private JPanel parentPanel;
    private JPanel primaryPanel;
    private JPanel footerPanel;
    private JPanel headerPanel;
    private JPanel centerPanel;
    private JPasswordField passwordField;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPasswordField confirmPasswordField;
    private JLabel confirmPasswordLabel;
    private JLabel passwordLabel;
    private JLabel errorLabel;

    public ChangePassword(Session session) {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                session.setActivePanel(session.getLogin().getParentPanel());
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText();
                String confirmPassword = confirmPasswordField.getText();

                if (password.equals("") || confirmPassword.equals("")) {
                    errorLabel.setText("Please fill out all fields.");
                } else {
                    boolean longEnough = false;
                    boolean hasNumbers = false;
                    boolean hasCaptials = false;
                    boolean hasLowercases = false;
                    boolean hasSymbols = false;
                    boolean passwordsMatch = false;

                    // check if password is long enough
                    if (password.length() >= 8) {
                        longEnough = true;
                    } else {
                        errorLabel.setText("Password must be at least 8 characters.");
                        longEnough = false;
                    }

                    // check if password has numbers
                    if (password.matches(".*\\d.*")) {
                        hasNumbers = true;
                    } else {
                        errorLabel.setText("Password must include numbers.");
                        hasNumbers = false;
                    }

                    // check if password has captials
                    if (password.matches(".*[A-Z].*")) {
                        hasCaptials = true;
                    } else {
                        errorLabel.setText("Password must include capitals.");
                        hasCaptials = false;
                    }

                    // check if password has lowercases
                    if (password.matches(".*[a-z].*")) {
                        hasLowercases = true;
                    } else {
                        errorLabel.setText("Password must include lowercases.");
                        hasLowercases = false;
                    }

                    // check if password has symbols
                    if (password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
                        hasSymbols = true;
                    } else {
                        errorLabel.setText("Password must include symbols.");
                        hasSymbols = false;
                    }

                    // check if passwords match
                    if (password.equals(confirmPassword)) {
                        passwordsMatch = true;
                    } else {
                        errorLabel.setText("Passwords do not match.");
                        passwordsMatch = false;
                    }

                    if (longEnough && hasNumbers && hasCaptials && hasLowercases && hasSymbols && passwordsMatch) {
                        session.getDatabase().updatePassword(session.getUserVerification().getUsernameField().getText(), password);
                        session.setUser(session.getDatabase().getUser(session.getUserVerification().getUsernameField().getText()));
                        session.setActivePanel(session.getHome().getParentPanel());
                    }
                }
            }
        });
    }

    private void createUIComponents() {
        errorLabel = new JLabel();
        errorLabel.setText(" ");
        errorLabel.setForeground(Color.RED);
    }

    public JPanel getParentPanel() {
        return parentPanel;
    }
}
