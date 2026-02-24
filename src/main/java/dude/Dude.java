package dude;

import dude.command.Command;
import java.io.File;

/**
 * Represents a chatbot application.
 */
public class Dude {
    /** The name of the bot. */
    public static final String BOT_NAME = "Dude";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a new Dude chatbot application.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Dude(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loadedTasks;
        try {
            loadedTasks = new TaskList(storage.load());
        } catch (DudeException e) {
            ui.showLoadingError();
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    /**
     * Runs the chatbot application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DudeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method to start the chatbot application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Dude("." + File.separator + "data" + File.separator + "dude.txt").run();
    }
}
