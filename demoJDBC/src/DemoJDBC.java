import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DemoJDBC extends MenuProgram {


    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = nextInt("Enter your choice: ");
            doTask(choice);
            if (choice == 0) running = false;
        }
    }

    public static int nextInt(String prompt) {
        System.out.print(prompt);
        int n = 0;
        boolean invalidInput = true;
        while (invalidInput) {
            try {
                Scanner sc = new Scanner(System.in);
                n = sc.nextInt();
                invalidInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again!");
                System.out.print(prompt);
            }
        }
        return n;
    }

    @Override
    protected void printMenu() {
        System.out.println("DEMO JDBC PROGRAM");
        System.out.println("1. Show tables");
        System.out.println("2. Add a table");
        System.out.println("3. Select all customer");
        System.out.println("4. Select customer by name");
        System.out.println("5. Insert record");
        System.out.println("6. Delete customer by id");
        System.out.println("7. Edit customer by id");
        System.out.println("0. Exit!");
    }

    @Override
    protected void doTask(int choice) {
        switch (choice) {
            case 1: showTables(); break;
            case 2: addTable(); break;
            case 3: showAllCustomers(); break;
            case 4: searchCustomer(); break;
            case 5: insertRecord(); break;
            case 6: deleteCustomer(); break;
            case 7: editCustomer(); break;
            case 0: break;
            default: System.out.println("Error! Try again!");
        }
    }

    private Connection connectDB() throws SQLException {
        String url = "jdbc:mysql://localhost/classicmodels";
        String user = "root";
        String pass = "admin";
        Connection conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }

    private void showTables() {
        System.out.println("All tables in classicmodels DB");
        try {
            Connection conn = connectDB();
            Statement stm = conn.createStatement();
            String sql = "select TABLE_NAME from information_schema.tables WHERE TABLE_TYPE='BASE TABLE'";
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                System.out.println(res.getString("TABLE_NAME"));
            }
            res.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void addTable() {
        try {
            Connection conn = connectDB();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter table name: ");
            String nameTable = sc.nextLine();
            String sql = "CREATE TABLE " + nameTable  + " (id int auto_increment primary key)";
            Statement stm = conn.createStatement();
            stm.execute(sql);
            System.out.println("Table " + nameTable + " is created successful");
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    private void showAllCustomers() {
        try {
            Connection conn = connectDB();
            String sql = "SELECT * from customers";
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                int number = res.getInt("customerNumber");
                String name = res.getString("customerName");
                System.out.println("(" + number + ", " + name + ")");
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void searchCustomer() {
        System.out.print("Enter customer number: ");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        try {
            Connection conn = connectDB();
            String sql = "SELECT * from customers WHERE customerNumber = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, number);
            ResultSet res = stm.executeQuery();
            if (!res.next()) System.out.println("Customer not found!");
            else do {
                number = res.getInt("customerNumber");
                String name = res.getString("customerName");
                System.out.println("(" + number + ", " + name + ")");
            } while (res.next());

            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void insertRecord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customers number: ");
        int customerNumber = sc.nextInt();

        Scanner sc1 = new Scanner(System.in);
        System.out.print("Enter customers name: ");
        String customerName = sc1.nextLine();

        Scanner sc2 = new Scanner(System.in);
        System.out.print("Enter customers last name: ");
        String contactLastName = sc2.nextLine();

        Scanner sc3 = new Scanner(System.in);
        System.out.print("Enter customers fist name: ");
        String contactFirstName = sc3.nextLine();

        Scanner sc4 = new Scanner(System.in);
        System.out.print("Enter phone: ");
        String phone = sc4.nextLine();

        Scanner sc5 = new Scanner(System.in);
        System.out.print("Enter address line1: ");
        String addressLine1 = sc5.nextLine();

        Scanner sc6 = new Scanner(System.in);
        System.out.print("Enter city: ");
        String city = sc6.nextLine();

        Scanner sc7 = new Scanner(System.in);
        System.out.print("Enter country: ");
        String country = sc7.nextLine();

        try {
            Connection conn = connectDB();
            String sql = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, " +
                    "phone, addressLine1, city, country)  " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, customerNumber);
            stm.setString(2, customerName);
            stm.setString(3, contactLastName);
            stm.setString(4, contactFirstName);
            stm.setString(5, phone);
            stm.setString(6, addressLine1);
            stm.setString(7,city);
            stm.setString(8, country);

            stm.executeUpdate();
            System.out.println("Insert record success!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void deleteCustomer() {
        System.out.print("Enter customer number: ");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        try {
            Connection conn = connectDB();
            String sql = "DELETE FROM customers WHERE customerNumber = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, number);

            int nRows = stm.executeUpdate();
            if (nRows == 0) {
                System.out.println("Customer not found!");
            } else {
                System.out.println("Delete successful!");
            }

            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void editCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer number: ");
        int number = sc.nextInt();
        Scanner sc1 = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String name = sc1.nextLine();
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Enter customer last name: ");
        String lastname = sc2.nextLine();
        try {
            Connection conn = connectDB();
            String sql = "update customers set customerName = ?, contactLastName = ? where customerNumber = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, lastname);
            stm.setInt(3, number);

            int nRows = stm.executeUpdate();
            if (nRows == 0) {
                System.out.println("Customer not found!");
            } else {
                System.out.println("Update successful!");
            }

            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
