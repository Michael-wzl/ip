package dude.task;

/**
 * Represents a Todo task without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return The formatted string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the file-friendly string representation of the todo task.
     *
     * @return The formatted string for saving to file.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
