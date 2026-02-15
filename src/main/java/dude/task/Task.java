package dude.task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the given description. The task is initially not
     * done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The formatted string with status icon and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the file-friendly string representation of the task.
     *
     * @return The formatted string for saving to file.
     */
    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns whether the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description string.
     */
    public String getDescription() {
        return description;
    }
}
