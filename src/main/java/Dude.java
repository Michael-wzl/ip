import java.util.Scanner;

/**
 * Represents a chatbot application.
 */
public class Dude {
    /** The name of the bot. */
    public static final String BOT_NAME = "Dude";

    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String[] tasks = new String[100];
        int taskCount = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            // Greet the user
            System.out.println(line);
            System.out.println(" Hello! I'm " + BOT_NAME + "!");
            System.out.println(" What can I do for you?");
            System.out.println(line);

            // Main interaction loop
            while (true) {
                String input = scanner.nextLine();

                switch (input) {
                case "bye" -> {
                    System.out.println(line);
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println(line);
                }
                case "list" -> {
                    // Display all stored tasks
                    System.out.println(line);
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks[i]);
                    }
                    System.out.println(line);
                }
                default -> {
                    // Add the task to the list
                    if (taskCount < 100) {
                        tasks[taskCount] = input;
                        taskCount++;
                        System.out.println(line);
                        System.out.println(" added: " + input);
                        System.out.println(line);
                    } else {
                        System.out.println(line);
                        System.out.println(" Sorry, task list is full!");
                        System.out.println(line);
                    }
                }
                }
            }
        }
    }
}
