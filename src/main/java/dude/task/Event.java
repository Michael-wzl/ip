package dude.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task that starts at a specific date/time and ends at a
 * specific date/time.
 */
public class Event extends Task {

    protected LocalDate fromDate;
    protected String fromString;
    protected LocalDate toDate;
    protected String toRawString;

    /**
     * Creates a new Event with the given description, start time and end time.
     *
     * @param description The description of the event task.
     * @param from The start date/time as a string.
     * @param to The end date/time as a string.
     */
    public Event(String description, String from, String to) {
        super(description);
        try {
            this.fromDate = LocalDate.parse(from);
            this.fromString = from;
        } catch (DateTimeParseException e) {
            this.fromDate = null;
            this.fromString = from;
        }
        try {
            this.toDate = LocalDate.parse(to);
            this.toRawString = to;
        } catch (DateTimeParseException e) {
            this.toDate = null;
            this.toRawString = to;
        }
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return The formatted string
     */
    @Override
    public String toString() {
        String fromDisplay = (fromDate != null)
                ? fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : fromString;
        String toDisplay = (toDate != null)
                ? toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : toRawString;
        return "[E]" + super.toString() + " (from: " + fromDisplay + " to: " + toDisplay + ")";
    }

    /**
     * Returns the file-friendly string representation of the event task.
     *
     * @return The formatted string for saving to file.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + fromString + " | " + toRawString;
    }
}
