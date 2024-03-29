package lenzj.dev.database;

import lenzj.dev.objects.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost/loginsystem";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    private List<User> userList;
    private Connection conn;

    public UserDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.userList = getUsers();

    }

    public void addUser(User user) {
        try {
            String sql = "INSERT INTO users (username, email, phone, password) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername().toLowerCase());
            stmt.setString(2, user.getEmail().toLowerCase());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
            this.userList = getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String username) {
        try {
            String sql = "DELETE FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.executeUpdate();
            this.userList = getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User user = new User(
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("password")
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public User getUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username.toLowerCase())) {
                return user;
            }
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email.toLowerCase())) {
                return user;
            }
        }
        return null;
    }

    public User getUserByPhone(String phone) {
        for (User user : userList) {
            if (user.getPhone().equals(phone)) {
                return user;
            }
        }
        return null;
    }

    public boolean checkUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username.toLowerCase()) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean usernameTaken(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean emailTaken (String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean phoneTaken (String phone) {
        for (User user : userList) {
            if (user.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public void updatePassword(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                try {
                    String sql = "UPDATE users SET password = ? WHERE username = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, password);
                    stmt.setString(2, username);
                    stmt.executeUpdate();
                    this.userList = getUsers();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateEmail(String username, String email) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                try {
                    String sql = "UPDATE users SET email = ? WHERE username = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, email);
                    stmt.setString(2, username);
                    stmt.executeUpdate();
                    this.userList = getUsers();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updatePhone(String username, String phone) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                try {
                    String sql = "UPDATE users SET phone = ? WHERE username = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, phone);
                    stmt.setString(2, username);
                    stmt.executeUpdate();
                    this.userList = getUsers();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void debugPrint() {
        System.out.println(
                "██╗   ██╗███████╗███████╗██████╗ ███████╗\n" +
                "██║   ██║██╔════╝██╔════╝██╔══██╗██╔════╝\n" +
                "██║   ██║███████╗█████╗  ██████╔╝███████╗\n" +
                "██║   ██║╚════██║██╔══╝  ██╔══██╗╚════██║\n" +
                "╚██████╔╝███████║███████╗██║  ██║███████║\n" +
                " ╚═════╝ ╚══════╝╚══════╝╚═╝  ╚═╝╚══════╝");
        for (User user : userList) {
            System.out.println("USERNAME: " + user.getUsername() + " | EMAIL: " + user.getEmail() + " | PHONE: " + user.getPhone() + " | PASSWORD: " + user.getPassword());
        }
    }

    public void purgeAllUsers() {
        try {
            String sql = "DELETE FROM users";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            this.userList = getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}