import java.util.Scanner;

/**
 * Represents a chatbot application.
 */
public class Dude {
    /** The name of the bot. */
    public static final String BOT_NAME = "Dude";

    public static void main(String[] args) {
        String line = "____________________________________________________________";

        try (Scanner scanner = new Scanner(System.in)) {
            // Greet the user
            System.out.println(line);
            System.out.println(" Hello! I'm " + BOT_NAME + "!");
            System.out.println(" What can I do for you?");
            System.out.println(line);

            // Main interaction loop
            while (true) {
                String input = scanner.nextLine();

                if (input.equals("bye")) {
                    System.out.println(line);
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                } else {
                    // Echo the command
                    System.out.println(line);
                    System.out.println(" " + input);
                    System.out.println(line);
                }
            }
        }
    }
}
