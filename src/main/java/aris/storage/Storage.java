package aris.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import aris.list.TaskList;
import aris.task.Deadline;
import aris.task.Event;
import aris.task.Task;
import aris.task.Todo;

/**
 * Handles loading and saving tasks to a file for persistent storage.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     * @param filePath The path to the file used for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from a file into the provided TaskList.
     * @param list The TaskList to load tasks into.
     * @throws FileNotFoundException If the file does not exist.
     */
    public void loadFile(TaskList list) throws FileNotFoundException, IllegalArgumentException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            Task task;
            String input = s.nextLine();
            String[] parts = input.split(" \\| ", 3); // split task type, isDone and task

            if (parts.length != 3) {
                throw new IllegalArgumentException();
            }

            String taskType = parts[0];
            int isDone = Integer.parseInt(parts[1]);
            String taskStr = parts[2];

            if (taskType.equals("T")) {
                task = new Todo(taskStr, isDone);
            } else if (taskType.equals("D")) {
                String[] deadlinePart = taskStr.split(" \\| ", 2);
                String deadlineDescription = deadlinePart[0];
                String deadline = deadlinePart[1];
                task = new Deadline(deadlineDescription, isDone, deadline);
            } else if (taskType.equals("E")) {
                String[] eventPart = taskStr.split(" \\| ", 2);
                String eventDescription = eventPart[0];
                String periodString = eventPart[1];
                String[] period = periodString.split("-", 2);
                task = new Event(eventDescription, isDone, period[0], period[1]);
            } else {
                throw new IllegalArgumentException();
            }

            list.addTask(task);
        }
    }

    /**
     * Saves the current tasks in the TaskList to a file.
     * @param list The TaskList containing tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveFile(TaskList list) throws IOException {
        ensureFileExists();
        FileWriter fw = new FileWriter(filePath);
        fw.write(String.valueOf(list.toFile()));
        fw.close();
    }

    /**
     * Ensures the necessary directory and file exist before saving tasks.
     * @throws IOException If an error occurs while creating the file.
     */
    private static void ensureFileExists() throws IOException {
        File directory = new File("./data/");
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }

        File file = new File("./data/Aris.txt");
        if (!file.exists()) {
            file.createNewFile(); // Create the file if it doesn't exist
        }
    }
}
