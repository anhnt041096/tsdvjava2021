package control;

import model.ProductLines;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductLineDA extends DataAccess {
    private final String table = "productlines";
    private final String colID = "productLine";

        public ArrayList<ProductLines> searchAllProductLines() throws SQLException {
        ResultSet rs = selectAll(table);
        ArrayList<ProductLines> lines = new ArrayList<>();

        while (rs.next()) {
            String pLine = rs.getString("productLine");
            String textDesc = rs.getString("textDescription");
            String htlmDesc = rs.getString("htmlDescription");
            ProductLines productLines = new ProductLines(pLine, textDesc, htlmDesc);
            lines.add(productLines);
        }
        conn.close();
        return lines;
    }

    public ProductLines searchProductLine(String pl) throws SQLException {
        String sql = "SELECT * FROM " + table + " WHERE " + colID + " = ?";
        Connection conn = connectDB();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, pl);

        ResultSet rs = stm.executeQuery();
        ProductLines productLines = null;
        if (rs.next()) {
            String pLine = rs.getString("productLine");
            String textDesc = rs.getString("textDescription");
            String htlmDesc = rs.getString("htmlDescription");

            productLines = new ProductLines(pLine, textDesc, htlmDesc);
            conn.close();
            return productLines;
        } else {
            conn.close();
            return null;
        }
    }

    public void insertRecord(ProductLines productLines) throws SQLException {
        String pLine = productLines.getProductLine();
        String textDesc = productLines.getTextDescription();
        String htmlDesc = productLines.getHtmlDescription();

        Connection conn = connectDB();
        String sql = "INSERT INTO " + table + "(productLine, textDescription, htmlDescription) VALUES(?, ? ,?)";
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setString(1, pLine);
        stm.setString(2, textDesc);
        stm.setString(3, htmlDesc);
        stm.executeUpdate();

        conn.close();
    }

    public boolean deleteProductLine(ProductLines productLines) throws SQLException {
        String pLine = productLines.getProductLine();
        boolean nRows = deleteByID(table, colID, pLine);
        if (nRows) {
            return true;
        } else {
            return false;
        }
    }

    public boolean editProductLine(ProductLines productLines) throws SQLException {
        String productLine = productLines.getProductLine();
        String textDescription = productLines.getTextDescription();
        String htmlDescription = productLines.getHtmlDescription();

        Connection conn = connectDB();
        String sql = "UPDATE " + table + " SET textDescription = ?, htmlDescription = ? WHERE " + productLine + " = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, textDescription);
        stm.setString(2, htmlDescription);
        stm.setString(3,productLine);

        int nRows = stm.executeUpdate();
        conn.close();

        return nRows != 0;
    }

}
