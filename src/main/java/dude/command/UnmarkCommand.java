package dude.command;

import dude.DudeException;
import dude.Storage;
import dude.TaskList;
import dude.Ui;

/**
 * Represents a command to unmark a task.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates a new UnmarkCommand.
     *
     * @param index The index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DudeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DudeException("Task number " + (index + 1) + " is out of range. You have " + tasks.getSize() + " task(s).");
        }
        tasks.getTask(index).markAsNotDone();
        ui.showMessage(" OK, I've marked this task as not done yet:");
        ui.showMessage("   " + tasks.getTask(index));
        storage.save(tasks.getTasks());
    }
}
