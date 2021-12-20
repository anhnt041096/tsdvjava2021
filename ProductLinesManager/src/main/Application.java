package main;

import view.ProductLineManager;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        ProductLineManager AnhAnh = new ProductLineManager();
        try {
            AnhAnh.run();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
