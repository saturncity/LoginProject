package lenzj.dev.gui.forgotpages;

import lenzj.dev.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPassword {
    private JPanel parentPanel;
    private JPanel headerPanel;
    private JPanel primaryPanel;
    private JPanel footerPanel;
    private JPanel centerPanel;
    private JTextField usernameField;
    private JButton verifyButton;
    private JButton backButton;
    private JLabel usernameLabel;
    private JLabel errorLabel;

    public ForgotPassword(Session session) {

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                session.getLogin().getUsernameField().setText(usernameField.getText());
                session.setActivePanel(session.getLogin().getParentPanel());
            }
        });
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameField.getText().equals("")) {
                    errorLabel.setText("Please fill out all fields.");
                } else {
                    if (session.getDatabase().checkUser(usernameField.getText())) {
                        session.getUserVerification().getUsernameField().setText(usernameField.getText());
                        session.setActivePanel(session.getUserVerification().getParentPanel());
                    } else {
                        errorLabel.setText("User does not exist.");
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

    public JTextField getUsernameField() {
        return usernameField;
    }
}
