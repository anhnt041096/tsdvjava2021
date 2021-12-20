package view;

import control.ProductLineDA;
import model.ProductLines;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductLineManager extends MenuProgram{

    @Override
    protected void printMenu() {
        System.out.println(
                "1. Show all.\n"
                +"2. Search by product line.\n"
                +"3. Insert record.\n"
                +"4. Delete by product line.\n"
                +"5. Edit by product line.\n"
                +"0. Exit!"
        );
    }

    @Override
    protected void doTask(int choice) throws SQLException {
        switch (choice) {
            case 1: showAllProduct(); break;
            case 2: showProduct(); break;
            case 3: addProductLine();break;
            case 4: deleteProduct(); break;
            case 5: updateProduct(); break;
            case 0: break;
            default: System.out.println("ERROR! TRY AGAIN!");
        }
    }

    public ArrayList<ProductLines> allProductLine() throws SQLException {
        ProductLineDA productLineDA = new ProductLineDA();
        ArrayList<ProductLines> lines = productLineDA.searchAllProductLines();
        return lines;
    }

    public void showAllProduct() throws SQLException {
        ProductLineDA productLineDA = new ProductLineDA();
        ArrayList<ProductLines> lines = productLineDA.searchAllProductLines();
        System.out.println(lines);
    }

    public void showProduct() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product line: ");
        String pl = sc.nextLine();
        ProductLineDA productLineDA = new ProductLineDA();
        ProductLines productLines = productLineDA.searchProductLine(pl);
        productLines.show();
    }

    public void addProductLine() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product line: ");
        String pLine = sc.nextLine();
        System.out.print("Enter text description: ");
        String textDesc = sc.nextLine();
        System.out.print("Enter html description: ");
        String htmlDesc = sc.nextLine();

        ProductLines productLine = new ProductLines(pLine, textDesc, htmlDesc);
        ProductLineDA productLineDA = new ProductLineDA();
        productLineDA.insertRecord(productLine);
        System.out.println("Insert record success!");
    }

    public void deleteProduct() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product line: ");
        String pLine = sc.nextLine();
        ProductLines productLine = new ProductLines(pLine);
        ProductLineDA productLineDA = new ProductLineDA();
        boolean nRows = productLineDA.deleteProductLine(productLine);
        if(nRows) {
            System.out.println("Delete product success!");
        } else {
            System.out.println("Delete product failed!");
        }
    }

    public void updateProduct() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product line: ");
        String pLine = sc.nextLine();
        System.out.print("Enter text description: ");
        String textDesc = sc.nextLine();
        sc.next();
        System.out.print("Enter html description: ");
        String htmlDesc = sc.nextLine();

        ProductLineDA productLineDA = new ProductLineDA();
        ProductLines productLine = new ProductLines(pLine, textDesc, htmlDesc);
        boolean nRows = productLineDA.editProductLine(productLine);
        if(nRows) {
            System.out.println("Update product success!");
        } else {
            System.out.println("Update product failed!");
        }
    }
}
