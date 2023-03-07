package lenzj.dev;

import lenzj.dev.database.ReservationDatabase;
import lenzj.dev.gui.Home;
import lenzj.dev.gui.Login;
import lenzj.dev.gui.Register;
import lenzj.dev.gui.reservationpages.Reservation;
import lenzj.dev.gui.forgotpages.ChangePassword;
import lenzj.dev.gui.forgotpages.ForgotPassword;
import lenzj.dev.gui.forgotpages.ForgotUsername;
import lenzj.dev.gui.forgotpages.UserVerification;
import lenzj.dev.gui.reservationpages.Table;
import lenzj.dev.objects.User;
import lenzj.dev.database.UserDatabase;


import javax.swing.*;
import java.awt.*;

public class Session {
    public JPanel activePanel;
    public Login login = new Login(this);
    public Register register = new Register(this);
    public Home home = new Home(this);
    public Reservation reservation = new Reservation(this);
    public Table table = new Table(this);
    public ForgotPassword forgotPassword = new ForgotPassword(this);
    public ForgotUsername forgotUsername = new ForgotUsername(this);
    public UserVerification userVerification = new UserVerification(this);
    public ChangePassword changePassword = new ChangePassword(this);
    public JFrame frame = new JFrame();
    public User user;
    public UserDatabase database = new UserDatabase();
    public ReservationDatabase reservationDatabase = new ReservationDatabase();
    public GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
    public GraphicsDevice device = graphics.getDefaultScreenDevice();

    public Session() {
        frame.setContentPane(login.getParentPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle("LenzOS");
        frame.setVisible(true);

        setActivePanel(login.getParentPanel());

//        database.purgeAllUsers();
//        reservationDatabase.purgeReservations();

        database.debugPrint();
        reservationDatabase.debugPrint();
    }

    public JPanel getActivePanel() {
        return activePanel;
    }

    public void setActivePanel(JPanel panel) {
        activePanel = panel;
        frame.setContentPane(activePanel);
        frame.revalidate();
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

    public ForgotPassword getForgotPassword() {
        return forgotPassword;
    }

    public ForgotUsername getForgotUsername() {
        return forgotUsername;
    }

    public UserVerification getUserVerification() {
        return userVerification;
    }

    public ChangePassword getChangePassword() {
        return changePassword;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public GraphicsEnvironment getGraphics() {
        return graphics;
    }

    public GraphicsDevice getDevice() {
        return device;
    }

    public Table getTable() {
        return table;
    }

    public ReservationDatabase getReservationDatabase() {
        return reservationDatabase;
    }
}
