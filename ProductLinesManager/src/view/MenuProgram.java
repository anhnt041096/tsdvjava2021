package view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class MenuProgram {

    public void run() throws SQLException {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = nextInt("Enter your choice: ");
            doTask(choice);
            if(choice == 0) running = false;
        }
    }

    public int nextInt(String prompt) {
        System.out.print(prompt);
        int n = 0;
        boolean invalidInput = true;
        while (invalidInput) {
            try {
                Scanner sc = new Scanner(System.in);
                n = sc.nextInt();
                invalidInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Try again!");
                System.out.print(prompt);
            }
        }
        return n;
    }

    protected abstract void printMenu();
    protected abstract void doTask(int choice) throws SQLException;
}
