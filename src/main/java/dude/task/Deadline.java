package dude.task;

/**
 * Represents a Deadline task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Creates a new Deadline with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date/time as a string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The formatted string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
