import java.util.Scanner;

/**
 * Represents a chatbot application.
 */
public class Dude {
    /** The name of the bot. */
    public static final String BOT_NAME = "Dude";

    public static void main(String[] args) {
        String line = "____________________________________________________________";
        Task[] tasks = new Task[100];
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

                if (input.equals("bye")) {
                    System.out.println(line);
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                } else if (input.equals("list")) {
                    // Display all stored tasks
                    System.out.println(line);
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + "." + tasks[i]);
                    }
                    System.out.println(line);
                } else if (input.startsWith("mark ")) {
                    // Mark task as done
                    int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsDone();
                        System.out.println(line);
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks[taskIndex]);
                        System.out.println(line);
                    } else {
                        System.out.println(line);
                        System.out.println(" Invalid task number!");
                        System.out.println(line);
                    }
                } else if (input.startsWith("unmark ")) {
                    // Mark task as not done
                    int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsNotDone();
                        System.out.println(line);
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks[taskIndex]);
                        System.out.println(line);
                    } else {
                        System.out.println(line);
                        System.out.println(" Invalid task number!");
                        System.out.println(line);
                    }
                } else {
                    // Add the task to the list
                    if (taskCount < 100) {
                        tasks[taskCount] = new Task(input);
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
