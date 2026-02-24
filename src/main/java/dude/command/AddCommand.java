package dude.command;

import dude.DudeException;
import dude.Storage;
import dude.TaskList;
import dude.Ui;
import dude.task.Task;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Creates a new AddCommand.
     *
     * @param task The task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DudeException {
        tasks.addTask(task);
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + task);
        ui.showMessage(" Now you have " + tasks.getSize() + " tasks in the list.");
        storage.save(tasks.getTasks());
    }
}
