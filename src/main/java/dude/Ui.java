package dude;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private final Scanner scanner;
    private static final String LINE = "____________________________________________________________";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm " + Dude.BOT_NAME + "!");
        System.out.println(" What can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println(" Dude says: " + message);
    }

    public void showLoadingError() {
        System.out.println(" Dude says: Error loading tasks from file.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
