package lenzj.dev;

import javax.swing.*;
import java.awt.event.*;

public class Register {
    JPanel parentPanel;
    private JPanel headerPanel;
    private JPanel footerPanel;
    private JPanel primaryPanel;
    private JPanel centerPanel;
    private JLabel headerLabel;
    private JLabel footerLabel;
    private JPanel textFieldPanel;
    JTextField usernameField;
    private JTextField emailField;
    private JTextField phoneField;
    JPasswordField passwordField;
    private JPanel buttonPanel;
    private JButton registerButton;
    private JPasswordField confirmPasswordField;
    private JButton returnButton;
    private JPanel offsetPanel;
    private JPanel conditionPanel;
    private JCheckBox totalCharactersCheckBox;
    private JCheckBox numbersCheckBox;
    private JCheckBox captialsCheckBox;
    private JCheckBox lowercasesCheckBox;
    private JCheckBox symbolsCheckBox;
    private JCheckBox passwordsMatchCheckBox;
    private JLabel creationConditionsLabel;
    private JCheckBox phoneIsnTTakenCheckBox;
    private JCheckBox emailIsnTTakenCheckBox;
    private JCheckBox userIsnTTakenCheckBox;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;

    public Register(Navigator navigator) {

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // write username & password from register to login
                navigator.login.usernameField.setText(usernameField.getText());
                navigator.login.passwordField.setText(passwordField.getText());

                // show login panel
                navigator.setActivePanel(navigator.login.parentPanel);
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkConditions();
            }
        });
        confirmPasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkConditions();
            }
        });
        phoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkConditions();
            }
        });
        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkConditions();
            }
        });
        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkConditions();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String password = new String(passwordField.getPassword());

                // create user
                navigator.user = new User(username, email, phone, password);

                // show home panel
                navigator.setActivePanel(navigator.home.parentPanel);
            }
        });
    }
    public void checkConditions() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // TODO: implement checks
        // check if username is taken
        boolean userNotTaken = true;

        // check if email is taken
        boolean emailNotTaken = true;

        // check if phone is taken
        boolean phoneNotTaken = true;

        // check if password is long enough
        boolean totalCharacters = password.length() >= 8;

        // check if password has numbers
        boolean numbers = password.matches(".*\\d.*");

        // check if password has captials
        boolean captials = password.matches(".*[A-Z].*");

        // check if password has lowercases
        boolean lowercases = password.matches(".*[a-z].*");

        // check if password has symbols
        boolean symbols = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

        // check if passwords match
        boolean passwordsMatch = false;
        if (!(password.equals(""))) {
            passwordsMatch = password.equals(confirmPassword);
        }


        // set checkboxes
        userIsnTTakenCheckBox.setSelected(userNotTaken);
        emailIsnTTakenCheckBox.setSelected(emailNotTaken);
        phoneIsnTTakenCheckBox.setSelected(phoneNotTaken);
        totalCharactersCheckBox.setSelected(totalCharacters);
        numbersCheckBox.setSelected(numbers);
        captialsCheckBox.setSelected(captials);
        lowercasesCheckBox.setSelected(lowercases);
        symbolsCheckBox.setSelected(symbols);
        passwordsMatchCheckBox.setSelected(passwordsMatch);

        // check if all conditions are met
        boolean conditionsMet = userNotTaken && emailNotTaken && phoneNotTaken && totalCharacters && numbers && captials && lowercases && symbols && passwordsMatch;
        if (conditionsMet) {
            registerButton.setEnabled(true);
        } else {
            registerButton.setEnabled(false);
        }
    }
}
