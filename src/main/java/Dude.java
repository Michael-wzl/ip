import java.util.Scanner;

/**
 * Represents a chatbot application.
 */
public class Dude {
    /** The name of the bot. */
    public static final String BOT_NAME = "Dude";
    /** Maximum number of tasks allowed. */
    private static final int MAX_TASKS = 100;

    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    /**
     * Parses and executes the user command.
     *
     * @param input The user input string.
     * @throws DudeException If the command is invalid or has errors.
     */
    private static void processCommand(String input) throws DudeException {
        if (input.equals("list")) {
            printTaskList();
        } else if (input.equals("mark") || input.startsWith("mark ")) {
            handleMark(input);
        } else if (input.equals("unmark") || input.startsWith("unmark ")) {
            handleUnmark(input);
        } else if (input.equals("todo") || input.startsWith("todo ")) {
            handleTodo(input);
        } else if (input.equals("deadline") || input.startsWith("deadline ")) {
            handleDeadline(input);
        } else if (input.equals("event") || input.startsWith("event ")) {
            handleEvent(input);
        } else {
            throw new DudeException("Hey, I don't understand what '" + input + "' means. "
                                            + "Try: todo, deadline, event, list, mark, unmark, bye.");
        }
    }

    /**
     * Displays all tasks in the list.
     */
    private static void printTaskList() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
        }
    }

    /**
     * Parses the task index from the input and validates it.
     *
     * @param input The user input string.
     * @param commandLength The length of the command keyword (e.g., "mark " is 5).
     * @param commandName The name of the command for error messages.
     * @return The validated task index (0-based).
     * @throws DudeException If task number missing/not number/out of range.
     */
    private static int parseTaskIndex(String input, int commandLength, String commandName)
            throws DudeException {
        if (input.length() <= commandLength) {
            throw new DudeException("Please provide a task number. Usage: "
                    + commandName + " <task number>");
        }
        String numberStr = input.substring(commandLength).trim();
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(numberStr) - 1;
        } catch (NumberFormatException e) {
            throw new DudeException("'" + numberStr + "' is not a valid number. "
                    + "Usage: " + commandName + " <task number>");
        }
        if (taskIndex < 0 || taskIndex >= taskCount) {
            throw new DudeException("Task number " + (taskIndex + 1) + " is out of range. " + "You have " + taskCount
                                            + " task(s).");
        }
        return taskIndex;
    }

    /**
     * Handles the "mark" command.
     *
     * @param input The user input string.
     * @throws DudeException If the task number is invalid.
     */
    private static void handleMark(String input) throws DudeException {
        int taskIndex = parseTaskIndex(input, 5, "mark");
        tasks[taskIndex].markAsDone();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks[taskIndex]);
    }

    /**
     * Handles the "unmark" command.
     *
     * @param input The user input string.
     * @throws DudeException If the task number is invalid.
     */
    private static void handleUnmark(String input) throws DudeException {
        int taskIndex = parseTaskIndex(input, 7, "unmark");
        tasks[taskIndex].markAsNotDone();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks[taskIndex]);
    }

    /**
     * Handles the "todo" command.
     *
     * @param input The user input string.
     * @throws DudeException If description empty or task list is full.
     */
    private static void handleTodo(String input) throws DudeException {
        if (input.length() <= 4 || input.substring(5).trim().isEmpty()) {
            throw new DudeException("The description of a todo cannot be empty. "
                    + "Usage: todo <description>");
        }
        if (taskCount >= MAX_TASKS) {
            throw new DudeException("Task list is full! You cannot add more than "
                    + MAX_TASKS + " tasks.");
        }
        String description = input.substring(5).trim();
        tasks[taskCount] = new Todo(description);
        taskCount++;
        printAddedTask();
    }

    /**
     * Handles the "deadline" command.
     *
     * @param input The user input string.
     * @throws DudeException If the format is invalid or the task list is full.
     */
    private static void handleDeadline(String input) throws DudeException {
        if (input.length() <= 8 || input.substring(9).trim().isEmpty()) {
            throw new DudeException("The description of a deadline cannot be empty. "
                                            + "Usage: deadline <description> /by <time>");
        }
        String content = input.substring(9);
        int byIndex = content.indexOf(" /by ");
        if (byIndex == -1) {
            throw new DudeException("Missing '/by' in deadline command. " + "Usage: deadline <description> /by <time>");
        }
        String description = content.substring(0, byIndex).trim();
        String by = content.substring(byIndex + 5).trim();
        if (description.isEmpty()) {
            throw new DudeException("The description of a deadline cannot be empty. "
                                            + "Usage: deadline <description> /by <time>");
        }
        if (by.isEmpty()) {
            throw new DudeException("The deadline time cannot be empty. " + "Usage: deadline <description> /by <time>");
        }
        if (taskCount >= MAX_TASKS) {
            throw new DudeException("Task list is full! You cannot add more than " + MAX_TASKS + " tasks.");
        }
        tasks[taskCount] = new Deadline(description, by);
        taskCount++;
        printAddedTask();
    }

    /**
     * Handles the "event" command.
     *
     * @param input The user input string.
     * @throws DudeException If the format is invalid or the task list is full.
     */
    private static void handleEvent(String input) throws DudeException {
        if (input.length() <= 5 || input.substring(6).trim().isEmpty()) {
            throw new DudeException("The description of an event cannot be empty. "
                                            + "Usage: event <description> /from <start> /to <end>");
        }
        String content = input.substring(6);
        int fromIndex = content.indexOf(" /from ");
        int toIndex = content.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1) {
            throw new DudeException("Missing '/from' or '/to' in event command. "
                                            + "Usage: event <description> /from <start> /to <end>");
        }
        String description = content.substring(0, fromIndex).trim();
        String from = content.substring(fromIndex + 7, toIndex).trim();
        String to = content.substring(toIndex + 5).trim();
        if (description.isEmpty()) {
            throw new DudeException("The description of an event cannot be empty. "
                                            + "Usage: event <description> /from <start> /to <end>");
        }
        if (from.isEmpty()) {
            throw new DudeException("The start time of an event cannot be empty. "
                                            + "Usage: event <description> /from <start> /to <end>");
        }
        if (to.isEmpty()) {
            throw new DudeException("The end time of an event cannot be empty. "
                                            + "Usage: event <description> /from <start> /to <end>");
        }
        if (taskCount >= MAX_TASKS) {
            throw new DudeException("Task list is full! You cannot add more than " + MAX_TASKS + " tasks.");
        }
        tasks[taskCount] = new Event(description, from, to);
        taskCount++;
        printAddedTask();
    }

    /**
     * Prints the confirmation message after adding a task.
     */
    private static void printAddedTask() {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks[taskCount - 1]);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }

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
                }

                try {
                    System.out.println(line);
                    processCommand(input);
                    System.out.println(line);
                } catch (DudeException e) {
                    System.out.println(" Dude says: " + e.getMessage());
                    System.out.println(line);
                }
            }
        }
    }
}
