package dude.command;

import dude.DudeException;
import dude.Storage;
import dude.TaskList;
import dude.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage.
     * @throws DudeException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DudeException;

    /**
     * Returns whether this command should exit the application.
     *
     * @return true if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
