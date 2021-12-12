import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/classicmodels";
        String user = "root";
        String pass = "admin";
        String sql = "select * from customers";
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("customerName");
                String mobile = rs.getString("phone");
                String city = rs.getString("city");

                System.out.println("(" + name + ", " + mobile + ", " + city + ")");
            }

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
