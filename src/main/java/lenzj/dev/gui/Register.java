package lenzj.dev.gui;

import lenzj.dev.objects.User;
import lenzj.dev.database.UserDatabase;
import lenzj.dev.Session;

import javax.swing.*;
import java.awt.event.*;

public class Register {
    private JPanel parentPanel;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JButton registerButton;
    private JButton returnButton;
    private JCheckBox captialsCheckBox;
    private JCheckBox emailIsnTTakenCheckBox;
    private JCheckBox lowercasesCheckBox;
    private JCheckBox numbersCheckBox;
    private JCheckBox passwordsMatchCheckBox;
    private JCheckBox phoneIsnTTakenCheckBox;
    private JCheckBox symbolsCheckBox;
    private JCheckBox totalCharactersCheckBox;
    private JCheckBox userIsnTTakenCheckBox;
    private JLabel confirmPasswordLabel;
    private JLabel creationConditionsLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel phoneLabel;
    private JLabel usernameLabel;
    private JPanel buttonPanel;
    private JPanel centerPanel;
    private JPanel conditionPanel;
    private JPanel footerPanel;
    private JPanel headerPanel;
    private JPanel offsetPanel;
    private JPanel primaryPanel;
    private JPanel textFieldPanel;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JTextField phoneField;

    public Register(Session session) {
        /* when clicked, transfer information from fields and change panel */
        // event listener for when the return button is pushed
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // write username & password from register to login
                session.getLogin().getUsernameField().setText(usernameField.getText());
                session.getLogin().getPasswordField().setText(passwordField.getText());

                // show login panel
                session.setActivePanel(session.getLogin().getParentPanel());

                // clear fields
                usernameField.setText("");
                phoneField.setText("");
                emailField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkConditions(session.getDatabase());
            }
        });
        confirmPasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkConditions(session.getDatabase());
            }
        });
        phoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkConditions(session.getDatabase());
            }
        });
        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkConditions(session.getDatabase());
            }
        });
        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkConditions(session.getDatabase());
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
                User user = new User(username, email, phone, password);
                session.getDatabase().addUser(user);
                session.setUser(user);

                // show home panel
                session.setActivePanel(session.getHome().getParentPanel());

                // clear fields
                usernameField.setText("");
                phoneField.setText("");
                emailField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
            }
        });
    }

    public void checkConditions(UserDatabase database) {
        /* check information from fields, and set checkboxes accordingly, also user to create when valid */
        // get text from fields
        String username = usernameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // check if username is taken
        boolean userNotTaken = !database.usernameTaken(username);

        // check if email is taken
        boolean emailNotTaken = !database.emailTaken(email);

        // check if phone is taken
        boolean phoneNotTaken = !database.phoneTaken(phone);

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

    public JPanel getParentPanel() {
        return parentPanel;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }
}
