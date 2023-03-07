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
                    errorLabel.setText("Please fill out the field.");
                } else if (session.getDatabase().getUserByEmail(fieldField.getText()) != null) {
                    session.getLogin().getUsernameField().setText(session.getDatabase().getUserByEmail(fieldField.getText()).getUsername());
                    errorLabel.setText("Your username is " + session.getDatabase().getUserByEmail(fieldField.getText()).getUsername());
                } else if (session.getDatabase().getUserByPhone(fieldField.getText()) != null) {
                    session.getLogin().getUsernameField().setText(session.getDatabase().getUserByPhone(fieldField.getText()).getUsername());
                    errorLabel.setText("Your username is " + session.getDatabase().getUserByPhone(fieldField.getText()).getUsername());
                } else {
                    errorLabel.setText("User does not exist.");
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
