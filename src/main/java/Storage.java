import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void loadFile(TaskList list) throws FileNotFoundException {
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
            task.addTask(list);
        }
    }

    private void saveFile(TaskList list) throws IOException {
        ensureFileExists();
        StringBuilder s = new StringBuilder(); // StringBuilder for efficiency
        int i = 1;
        for (Iterator<Task> it = list.iterator(); it.hasNext(); i++) {
            Task item = it.next();
            s.append(item.fileFormat());
            if (it.hasNext()) { // line break except for last item; for formatting purposes
                s.append("\n");
            }
        }

        FileWriter fw = new FileWriter("./data/Aris.txt");
        fw.write(String.valueOf(s));
        fw.close();
    }

    private static void ensureFileExists() throws IOException {
        File directory = new File("./data/");
        if (!directory.exists()) {
            directory.mkdirs();  // Create the directory if it doesn't exist
        }

        File file = new File("./data/Aris.txt");
        if (!file.exists()) {
            file.createNewFile();  // Create the file if it doesn't exist
        }
    }
}
