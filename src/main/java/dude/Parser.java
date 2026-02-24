package dude;

import dude.command.AddCommand;
import dude.command.Command;
import dude.command.DeleteCommand;
import dude.command.ExitCommand;
import dude.command.FindCommand;
import dude.command.ListCommand;
import dude.command.MarkCommand;
import dude.command.UnmarkCommand;
import dude.task.Deadline;
import dude.task.Event;
import dude.task.Todo;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param fullCommand The full user input string.
     * @return The command to be executed.
     * @throws DudeException If the command is invalid or has errors.
     */
    public static Command parse(String fullCommand) throws DudeException {
        String input = fullCommand.trim();
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("mark") || input.startsWith("mark ")) {
            return new MarkCommand(parseTaskIndex(input, 5, "mark"));
        } else if (input.equals("unmark") || input.startsWith("unmark ")) {
            return new UnmarkCommand(parseTaskIndex(input, 7, "unmark"));
        } else if (input.equals("delete") || input.startsWith("delete ")) {
            return new DeleteCommand(parseTaskIndex(input, 7, "delete"));
        } else if (input.startsWith("find ")) {
            return new FindCommand(input.substring(5).trim());
        } else if (input.equals("todo") || input.startsWith("todo ")) {
            return parseTodo(input);
        } else if (input.equals("deadline") || input.startsWith("deadline ")) {
            return parseDeadline(input);
        } else if (input.equals("event") || input.startsWith("event ")) {
            return parseEvent(input);
        } else {
            throw new DudeException("Hey, I don't understand what '" + input + "' means. "
                                            + "Try: todo, deadline, event, list, mark, unmark, delete, bye.");
        }
    }

    private static int parseTaskIndex(String input, int commandLength, String commandName) throws DudeException {
        if (input.length() <= commandLength) {
            throw new DudeException("Please provide a task number. Usage: " + commandName + " <task number>");
        }
        String numberStr = input.substring(commandLength).trim();
        try {
            return Integer.parseInt(numberStr) - 1;
        } catch (NumberFormatException e) {
            throw new DudeException("'" + numberStr + "' is not a valid number. Usage: " + commandName
                                            + " <task number>");
        }
    }

    private static Command parseTodo(String input) throws DudeException {
        if (input.length() <= 4 || input.substring(5).trim().isEmpty()) {
            throw new DudeException("The description of a todo cannot be empty. Usage: todo <description>");
        }
        return new AddCommand(new Todo(input.substring(5).trim()));
    }

    private static Command parseDeadline(String input) throws DudeException {
        if (input.length() <= 8 || input.substring(9).trim().isEmpty()) {
            throw new DudeException("The description of a deadline cannot be empty. Usage: deadline <description> /by <time>");
        }
        String content = input.substring(9);
        int byIndex = content.indexOf(" /by ");
        if (byIndex == -1) {
            throw new DudeException("Missing '/by' in deadline command. Usage: deadline <description> /by <time>");
        }
        String description = content.substring(0, byIndex).trim();
        String by = content.substring(byIndex + 5).trim();
        if (description.isEmpty()) {
            throw new DudeException("The description of a deadline cannot be empty. Usage: deadline <description> /by <time>");
        }
        if (by.isEmpty()) {
            throw new DudeException("The deadline time cannot be empty. Usage: deadline <description> /by <time>");
        }
        return new AddCommand(new Deadline(description, by));
    }

    private static Command parseEvent(String input) throws DudeException {
        if (input.length() <= 5 || input.substring(6).trim().isEmpty()) {
            throw new DudeException("The description of an event cannot be empty. Usage: event <description> /from <start> /to <end>");
        }
        String content = input.substring(6);
        int fromIndex = content.indexOf(" /from ");
        int toIndex = content.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1) {
            throw new DudeException("Missing '/from' or '/to' in event command. Usage: event <description> /from <start> /to <end>");
        }
        String description = content.substring(0, fromIndex).trim();
        String from = content.substring(fromIndex + 7, toIndex).trim();
        String to = content.substring(toIndex + 5).trim();
        if (description.isEmpty()) {
            throw new DudeException("The description of an event cannot be empty. Usage: event <description> /from <start> /to <end>");
        }
        if (from.isEmpty()) {
            throw new DudeException("The start time of an event cannot be empty. Usage: event <description> /from <start> /to <end>");
        }
        if (to.isEmpty()) {
            throw new DudeException("The end time of an event cannot be empty. Usage: event <description> /from <start> /to <end>");
        }
        return new AddCommand(new Event(description, from, to));
    }
}
