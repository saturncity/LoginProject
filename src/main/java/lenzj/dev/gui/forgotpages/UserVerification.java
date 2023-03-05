package lenzj.dev.gui.forgotpages;

import lenzj.dev.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserVerification {
    private final String code = String.valueOf((int) (Math.random() * 1000000));
    private JPanel parentPanel;
    private JPanel footerPanel;
    private JPanel primaryPanel;
    private JPanel headerPanel;
    private JPanel centerPanel;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JButton sendToEmailButton;
    private JButton sendToPhoneButton;
    private JPanel phoneSimulationPanel;
    private JPanel codePanel;
    private JTextField codeField;
    private JButton verifyButton;
    private JLabel codeLabel;
    private JTextPane phoneTextPane;
    private JLabel errorLabel;

    public UserVerification(Session session) {


        sendToPhoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStringToPhoneSimulationPane("[MESSAGES] (" + session.getDatabase().getUser(usernameField.getText()).getPhone() + ") LenzOS: Your code is " + code);
            }
        });
        sendToEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStringToPhoneSimulationPane("[EMAIL] (" + session.getDatabase().getUser(usernameField.getText()).getEmail() + ") LenzOS: Your code is " + code);
            }
        });
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (codeField.getText().equals(code)) {
                    session.getForgotPassword().getUsernameField().setText(usernameField.getText());
                    session.setActivePanel(session.getChangePassword().getParentPanel());
                } else {
                    codeLabel.setText("Code is incorrect.");
                }
            }
        });
    }

    public JPanel getParentPanel() {
        return parentPanel;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void addStringToPhoneSimulationPane(String s) {
        phoneTextPane.setText(phoneTextPane.getText() + "\n> " + s);
    }

    private void createUIComponents() {
        errorLabel = new JLabel();
        errorLabel.setText(" ");
        errorLabel.setForeground(Color.RED);
    }
}
