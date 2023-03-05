package lenzj.dev.database;

import lenzj.dev.objects.ReservationEvent;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDatabase {
    final static String DB_URL = "jdbc:mysql://localhost/reservationsystem";
    final static String DB_USER = "root";
    final static String DB_PASS = "";
    private List<ReservationEvent> reservationList;
    private Connection conn;

    public ReservationDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.reservationList = getReservations();

    }

    private List<ReservationEvent> getReservations() {
        List<ReservationEvent> reservationList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reservations";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String date = rs.getString("date");
                String table = rs.getString("pdr");
                ReservationEvent reservation = new ReservationEvent(name, phone, date, table);
                reservationList.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

    public void addReservation(ReservationEvent reservation) {
        try {
            String sql = "INSERT INTO reservations (name, phone, date, pdr) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, reservation.getName());
            stmt.setString(2, reservation.getPhone());
            stmt.setString(3, reservation.getDate());
            stmt.setString(4, reservation.getTable());
            stmt.executeUpdate();
            this.reservationList = getReservations();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkReservation(String table, String date) {
        for (ReservationEvent reservation : reservationList) {
            if (reservation.getTable().equals(table) && reservation.getDate().equals(date)) {
                return false;
            }
        }
        return true;

    }

    public void debugPrint() {
        System.out.println(
                "██████╗ ███████╗███████╗███████╗██████╗ ██╗   ██╗ █████╗ ████████╗██╗ ██████╗ ███╗   ██╗███████╗\n" +
                "██╔══██╗██╔════╝██╔════╝██╔════╝██╔══██╗██║   ██║██╔══██╗╚══██╔══╝██║██╔═══██╗████╗  ██║██╔════╝\n" +
                "██████╔╝█████╗  ███████╗█████╗  ██████╔╝██║   ██║███████║   ██║   ██║██║   ██║██╔██╗ ██║███████╗\n" +
                "██╔══██╗██╔══╝  ╚════██║██╔══╝  ██╔══██╗╚██╗ ██╔╝██╔══██║   ██║   ██║██║   ██║██║╚██╗██║╚════██║\n" +
                "██║  ██║███████╗███████║███████╗██║  ██║ ╚████╔╝ ██║  ██║   ██║   ██║╚██████╔╝██║ ╚████║███████║\n" +
                "╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝");
        for (ReservationEvent reservation : reservationList) {
            System.out.println("NAME: " + reservation.getName() + " | PHONE: " + reservation.getPhone() + " | DATE: " + reservation.getDate() + " | TABLE: " + reservation.getTable());
        }
    }

    public ReservationEvent getReservation(String table, String date) {
        for (ReservationEvent reservation : reservationList) {
            if (reservation.getTable().equals(table) && reservation.getDate().equals(date)) {
                return reservation;
            }
        }
        return null;
    }
}
