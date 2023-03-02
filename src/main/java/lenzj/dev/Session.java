package lenzj.dev;

import lenzj.dev.gui.Home;
import lenzj.dev.gui.Login;
import lenzj.dev.gui.Register;
import lenzj.dev.objects.User;
import lenzj.dev.database.UserDatabase;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Session {
    public JPanel activePanel;
    public Login login = new Login(this);
    public Register register = new Register(this);
    public Home home = new Home(this);
    public JFrame frame = new JFrame();
    public User user;
    public UserDatabase database = new UserDatabase();
    public Session() {
        // start with login panel
        setActivePanel(login.getParentPanel());
    }

    public JPanel getActivePanel() {
        return activePanel;
    }

    public void setActivePanel(JPanel activePanel) {
        this.activePanel = activePanel;
        frame.setContentPane(activePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1024, 768);
        frame.setVisible(true);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDatabase getDatabase() {
        return database;
    }

    public Login getLogin() {
        return login;
    }

    public Register getRegister() {
        return register;
    }

    public Home getHome() {
        return home;
    }

    public JFrame getFrame() {
        return frame;
    }
}
