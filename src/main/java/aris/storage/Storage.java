package aris.storage;

import aris.list.TaskList;
import aris.task.Deadline;
import aris.task.Event;
import aris.task.Task;
import aris.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void loadFile(TaskList list) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            Task task;
            String input = s.nextLine();
            String[] parts = input.split(" \\| ", 3); // split task type, isDone and task

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
            } else {
                String[] eventPart = taskStr.split(" \\| ", 2);
                String eventDescription = eventPart[0];
                String periodString = eventPart[1];
                String[] period = periodString.split("-", 2);
                task = new Event(eventDescription, isDone, period[0], period[1]);
            }
            list.addTask(task);
        }
    }

    public void saveFile(TaskList list) throws IOException {
        ensureFileExists();
        FileWriter fw = new FileWriter("./data/aris.Aris.txt");
        fw.write(String.valueOf(list.toFile()));
        fw.close();
    }

    private static void ensureFileExists() throws IOException {
        File directory = new File("./data/");
        if (!directory.exists()) {
            directory.mkdirs();  // Create the directory if it doesn't exist
        }

        File file = new File("./data/aris.Aris.txt");
        if (!file.exists()) {
            file.createNewFile();  // Create the file if it doesn't exist
        }
    }
}
