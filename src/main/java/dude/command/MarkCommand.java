package dude.command;

import dude.DudeException;
import dude.Storage;
import dude.TaskList;
import dude.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a new MarkCommand.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DudeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DudeException("Task number " + (index + 1) + " is out of range. You have " + tasks.getSize()
                                            + " task(s).");
        }
        tasks.getTask(index).markAsDone();
        ui.showMessage(" Nice! I've marked this task as done:");
        ui.showMessage("   " + tasks.getTask(index));
        storage.save(tasks.getTasks());
    }
}
