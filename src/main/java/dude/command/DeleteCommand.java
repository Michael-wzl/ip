package dude.command;

import dude.DudeException;
import dude.Storage;
import dude.TaskList;
import dude.Ui;
import dude.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a new DeleteCommand.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DudeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DudeException("Task number " + (index + 1) + " is out of range. You have " + tasks.getSize() + " task(s).");
        }
        Task removedTask = tasks.removeTask(index);
        ui.showMessage(" Noted. I've removed this task:");
        ui.showMessage("   " + removedTask);
        ui.showMessage(" Now you have " + tasks.getSize() + " tasks in the list.");
        storage.save(tasks.getTasks());
    }
}
