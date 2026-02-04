import java.util.Scanner;

/**
 * Represents a chatbot application.
 */
public class Dude {
    /** The name of the bot. */
    public static final String BOT_NAME = "Dude";
    /** Maximum number of tasks allowed. */
    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        String line = "____________________________________________________________";
        Task[] tasks = new Task[MAX_TASKS];
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
                    try {
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
                    } catch (NumberFormatException e) {
                        System.out.println(line);
                        System.out.println(" Please enter a valid task number!");
                        System.out.println(line);
                    }
                } else if (input.startsWith("unmark ")) {
                    // Mark task as not done
                    try {
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
                    } catch (NumberFormatException e) {
                        System.out.println(line);
                        System.out.println(" Please enter a valid task number!");
                        System.out.println(line);
                    }
                } else if (input.startsWith("todo ")) {
                    // Add a todo task
                    String description = input.substring(5).trim();
                    if (taskCount < MAX_TASKS) {
                        tasks[taskCount] = new Todo(description);
                        taskCount++;
                        System.out.println(line);
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + tasks[taskCount - 1]);
                        System.out.println(" Now you have " + taskCount + " tasks in the list.");
                        System.out.println(line);
                    } else {
                        System.out.println(line);
                        System.out.println(" Sorry, task list is full!");
                        System.out.println(line);
                    }
                } else if (input.startsWith("deadline ")) {
                    // Add a deadline task
                    String content = input.substring(9);
                    int byIndex = content.indexOf(" /by ");
                    if (byIndex != -1) {
                        String description = content.substring(0, byIndex).trim();
                        String by = content.substring(byIndex + 5).trim();
                        if (taskCount < MAX_TASKS) {
                            tasks[taskCount] = new Deadline(description, by);
                            taskCount++;
                            System.out.println(line);
                            System.out.println(" Got it. I've added this task:");
                            System.out.println("   " + tasks[taskCount - 1]);
                            System.out.println(" Now you have " + taskCount + " tasks in the list.");
                            System.out.println(line);
                        } else {
                            System.out.println(line);
                            System.out.println(" Sorry, task list is full!");
                            System.out.println(line);
                        }
                    } else {
                        System.out.println(line);
                        System.out.println(" Invalid deadline format! Use: deadline <description> /by <time>");
                        System.out.println(line);
                    }
                } else if (input.startsWith("event ")) {
                    // Add an event task
                    String content = input.substring(6);
                    int fromIndex = content.indexOf(" /from ");
                    int toIndex = content.indexOf(" /to ");
                    if (fromIndex != -1 && toIndex != -1) {
                        String description = content.substring(0, fromIndex).trim();
                        String from = content.substring(fromIndex + 7, toIndex).trim();
                        String to = content.substring(toIndex + 5).trim();
                        if (taskCount < MAX_TASKS) {
                            tasks[taskCount] = new Event(description, from, to);
                            taskCount++;
                            System.out.println(line);
                            System.out.println(" Got it. I've added this task:");
                            System.out.println("   " + tasks[taskCount - 1]);
                            System.out.println(" Now you have " + taskCount + " tasks in the list.");
                            System.out.println(line);
                        } else {
                            System.out.println(line);
                            System.out.println(" Sorry, task list is full!");
                            System.out.println(line);
                        }
                    } else {
                        System.out.println(line);
                        System.out.println(" Invalid event format! Use: event <description> /from <start> /to <end>");
                        System.out.println(line);
                    }
                } else {
                    System.out.println(line);
                    System.out.println(" Unknown command: " + input);
                    System.out.println(line);
                }
            }
        }
    }
}
