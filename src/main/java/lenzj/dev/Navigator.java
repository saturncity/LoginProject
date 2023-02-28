package lenzj.dev;

import javax.swing.*;

public class Navigator {
    JPanel activePanel;
    Login login = new Login(this);
    Register register = new Register(this);
    Home home = new Home(this);
    JFrame frame = new JFrame();
    User user;
    UserDatabase database = new UserDatabase();
    public Navigator() {
        // start with login panel
        setActivePanel(login.parentPanel);

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
}
