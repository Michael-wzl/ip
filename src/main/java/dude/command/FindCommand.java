package dude.command;

import java.util.ArrayList;

import dude.DudeException;
import dude.Storage;
import dude.TaskList;
import dude.Ui;
import dude.task.Task;

/**
 * Represents a command to find tasks by a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new FindCommand.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DudeException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showMessage(" No matching tasks found.");
        } else {
            ui.showMessage(" Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
    }
}
