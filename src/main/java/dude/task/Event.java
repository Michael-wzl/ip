package dude.task;

/**
 * Represents an Event task that starts at a specific date/time and ends at a
 * specific date/time.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Creates a new Event with the given description, start time and end time.
     *
     * @param description The description of the event task.
     * @param from The start date/time as a string.
     * @param to The end date/time as a string.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return The formatted string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
