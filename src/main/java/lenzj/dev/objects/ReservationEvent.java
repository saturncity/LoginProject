package lenzj.dev.objects;

public class ReservationEvent {
    private String name;
    private String phone;
    private String date;
    private String table;

    public ReservationEvent(String name, String phone, String date, String table) {
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.table = table;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
