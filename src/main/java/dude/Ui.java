package dude;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private final Scanner scanner;
    private static final String LINE = "____________________________________________________________";

    /**
     * Creates a new Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm " + Dude.BOT_NAME + "!");
        System.out.println(" What can I do for you?");
        showLine();
    }

    /**
     * Shows a line divider.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Reads a command from the user.
     *
     * @return The command string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows an error message.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        System.out.println(" Dude says: " + message);
    }

    /**
     * Shows a loading error message.
     */
    public void showLoadingError() {
        System.out.println(" Dude says: Error loading tasks from file.");
    }

    /**
     * Shows a general message.
     *
     * @param message The message to show.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
