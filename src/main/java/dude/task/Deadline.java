package dude.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected LocalDate byDate;
    protected String byString;

    /**
     * Creates a new Deadline with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date/time as a string.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.byDate = LocalDate.parse(by);
            this.byString = by;
        } catch (DateTimeParseException e) {
            this.byDate = null;
            this.byString = by;
        }
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The formatted string
     */
    @Override
    public String toString() {
        String byDisplay = (byDate != null) ? byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : byString;
        return "[D]" + super.toString() + " (by: " + byDisplay + ")";
    }

    /**
     * Returns the file-friendly string representation of the deadline task.
     *
     * @return The formatted string for saving to file.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + byString;
    }
}
