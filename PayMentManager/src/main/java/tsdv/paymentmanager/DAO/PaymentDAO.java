package tsdv.paymentmanager.DAO;

import tsdv.paymentmanager.model.Payment;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;

public class PaymentDAO {
    private String url = "jdbc:mysql://localhost/classicmodels";
    private String user = "root";
    private String pass = "admin";

    private static final String INSERT_PAYMENT_SQL = "INSERT INTO payments VALUES (?, ?, ?, ?)";
    private static final String SEARCH_BY_ID = "SELECT * FROM payments WHERE customerNumber = ? AND checkNumber = ?";
    private static final String SEARCH_ALL_PAYMENT = "SELECT * FROM payments";
    private static final String DELETE_PAYMENTS_BY_ID = "DELETE FROM payments WHERE customerNumber = ? AND checkNumber = ?";
    private static final String UPDATE_PAYMENTS_BY_ID = "UPDATE payments SET paymentDate = ?, amount = ? "
            + "WHERE customerNumber = ? AND checkNumber = ?";

    public PaymentDAO() {
    }

    protected Connection connectDB() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public ArrayList<Payment> selectAllPayment() throws SQLException {
        ArrayList<Payment> paymentlist = new ArrayList<>();
        Connection conn = connectDB();
        try {
            PreparedStatement stm = conn.prepareStatement(SEARCH_ALL_PAYMENT);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int customerNumber = rs.getInt("customerNumber");
                String checkNumber = rs.getString("checkNumber");
                Date paymentDate = rs.getDate("paymentDate");
                double amount = rs.getDouble("amount");
                Payment payment = new Payment(customerNumber, checkNumber, paymentDate, amount);
                paymentlist.add(payment);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        conn.close();
        return paymentlist;
    }

    public Payment selectPayment(int customerNumber, String checkNumber) throws SQLException {
        Payment payment = null;
        Connection conn = connectDB();
        try {
            PreparedStatement stm = conn.prepareStatement(SEARCH_BY_ID);
            stm.setInt(1, customerNumber);
            stm.setString(2, checkNumber);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Date paymentDate = rs.getDate("paymentDate");
                double amount = rs.getDouble("amount");
                payment = new Payment(customerNumber, checkNumber, paymentDate, amount);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        conn.close();
        return payment;
    }

    public void insertPayment(Payment payment) throws SQLException {
        int customerNumber = payment.getCustomerNumber();
        String checkNumber = payment.getCheckNumber();
        Date paymentDate = payment.getPaymentDate();
        double amount = payment.getAmount();
        Connection conn = connectDB();
        try {
            PreparedStatement stm = conn.prepareStatement(INSERT_PAYMENT_SQL);
            stm.setInt(1, customerNumber);
            stm.setString(2, checkNumber);
            stm.setDate(3, paymentDate);
            stm.setDouble(4, amount);
            stm.executeUpdate();
            System.out.println("Insert payment success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
    }

    public boolean deletePayment(int customerNumber, String checkNumber) throws SQLException {
        Connection conn = connectDB();
        boolean rowdelete = false;
        try {
            PreparedStatement stm = conn.prepareStatement(DELETE_PAYMENTS_BY_ID);
            stm.setInt(1, customerNumber);
            stm.setString(2, checkNumber);
            rowdelete = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        conn.close();
        return rowdelete;
    }

    public boolean updatePayment(Payment payment) throws SQLException {
        boolean rowUpdate = false;
        int customerNumber = payment.getCustomerNumber();
        String checkNumber = payment.getCheckNumber();
        Date paymentDate = payment.getPaymentDate();
        double amount = payment.getAmount();
        Connection conn = connectDB();
        try {
            PreparedStatement stm = conn.prepareStatement(UPDATE_PAYMENTS_BY_ID);
            stm.setDate(1, paymentDate);
            stm.setDouble(2, amount);
            stm.setInt(3, customerNumber);
            stm.setString(4, checkNumber);
            rowUpdate = stm.executeUpdate() > 0;
            System.out.println("Update payment success!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        conn.close();
        return rowUpdate;
    }

//    public static void main(String[] args) {
//        Date date = Date.valueOf("1996-10-04");
//        Payment payment = new Payment(103, "AB9876543", date, 68000.11);
//        try {
//            new PaymentDAO().insertPayment(payment);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            System.out.println(new PaymentDAO().deletePayment(103, "HQ336336"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            Payment payment = new PaymentDAO().selectPayment(103, "OM314933");
//            System.out.println(payment);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//    }
}
