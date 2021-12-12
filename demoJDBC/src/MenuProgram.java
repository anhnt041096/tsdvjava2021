import java.util.Scanner;

public abstract class MenuProgram {
    protected final int EXIT = 0;
    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getChoice();
            doTask(choice);
            running = choice != EXIT;
        }
    }

    protected abstract void printMenu();
    protected abstract void doTask(int choice);

    protected int getChoice() {
        System.out.print("Enter your choice: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        return choice;
    }
}