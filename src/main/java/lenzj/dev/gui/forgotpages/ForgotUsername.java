package lenzj.dev.gui.forgotpages;

import lenzj.dev.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotUsername {

    private JPanel parentPanel;
    private JPanel headerPanel;
    private JPanel primaryPanel;
    private JPanel footerPanel;
    private JTextField fieldField;
    private JButton checkUserButton;
    private JButton backButton;
    private JLabel errorLabel;
    private JLabel fieldLabel;

    public ForgotUsername(Session session) {

        checkUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fieldField.getText().equals("")) {
                    errorLabel.setText("Please fill out all fields.");
                } else if (session.getDatabase().getUser(fieldField.getText()) != null) {
                    session.getLogin().getUsernameField().setText(session.getDatabase().getUser(fieldField.getText()).getUsername());
                    errorLabel.setText("Your username is " + session.getDatabase().getUser(fieldField.getText()).getUsername());
                } else {
                    errorLabel.setText("No user is found with credentials: " + fieldLabel.getText().toLowerCase());
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                session.setActivePanel(session.getLogin().getParentPanel());
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
