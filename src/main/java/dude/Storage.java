package dude;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dude.task.Deadline;
import dude.task.Event;
import dude.task.Task;
import dude.task.Todo;

/**
 * Handles loading and saving tasks to a file on the hard disk.
 */
public class Storage {
    private static final String FILE_PATH = "." + File.separator + "data" + File.separator + "dude.txt";

    /**
     * Saves the current tasks to the file.
     *
     * @param tasks The list of tasks to save.
     */
    public static void save(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toFileString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(" Warning: Unable to save tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file into the given list.
     *
     * @param tasks The list to load tasks into.
     */
    public static void load(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                try {
                    Task task = parseLine(line);
                    tasks.add(task);
                } catch (DudeException e) {
                    System.out.println(" Warning: Skipping corrupted line in data file: " + line);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(" Warning: Unable to load tasks from file: " + e.getMessage());
        }
    }

    /**
     * Parses a single line from the data file and returns the corresponding
     * Task.
     *
     * @param line The line to parse.
     * @return The parsed Task object.
     * @throws DudeException If the line format is invalid.
     */
    private static Task parseLine(String line) throws DudeException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new DudeException("Invalid format");
        }

        String type = parts[0].trim();
        boolean isDone;
        String doneStr = parts[1].trim();
        if (doneStr.equals("1")) {
            isDone = true;
        } else if (doneStr.equals("0")) {
            isDone = false;
        } else {
            throw new DudeException("Invalid done status");
        }
        String description = parts[2].trim();

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) {
                throw new DudeException("Missing deadline info");
            }
            task = new Deadline(description, parts[3].trim());
            break;
        case "E":
            if (parts.length < 5) {
                throw new DudeException("Missing event info");
            }
            task = new Event(description, parts[3].trim(), parts[4].trim());
            break;
        default:
            throw new DudeException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
