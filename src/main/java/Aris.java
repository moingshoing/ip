import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Aris {
    TaskList list; // use of arraylist to store tasks
    Scanner userInput =  new Scanner(System.in); // scanner to read input

    private Aris() {
        this.list = new TaskList();
    }

    public static void main(String[] args) {
        Aris aris = new Aris();
        aris.runAris();
    }

    private void runAris() {
        Ui arisUi = new Ui(); // UI for format messages
        arisUi.greet(); // greet

        try {
            loadFile();
        } catch(FileNotFoundException e) {
            arisUi.format("No file found ┐(´ー｀)┌");
        }

        while(true) {
            String input =  userInput.nextLine();

            String[] parts = input.split(" ", 2); // split command and argument
            String commandStr = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";

            Command command = Command.convert(commandStr); // convert input to enum

            switch(command) { // use of switch because else if is ugly
                case LIST:
                    arisUi.format(list.printList());
                    break;

                case MARK:
                case UNMARK:
                case DELETE:
                    try {
                        int index = Integer.parseInt(argument);
                        if (command == Command.UNMARK) {
                            arisUi.format(list.markTaskUndone(index));
                        } else if (command == Command.MARK) {
                            arisUi.format(list.markTaskDone(index));
                        } else {
                            arisUi.format(list.deleteTask(index));
                        }
                    } catch (NumberFormatException e) { // number is not entered after mark/unmark
                        arisUi.format("This is not a number ┐(´ー｀)┌");
                    }
                    break;

                case TODO:
                case DEADLINE:
                case EVENT:
                    if (argument.isEmpty()) { // empty argument
                        arisUi.format("Try doing something instead ┐(´ー｀)┌");
                        break;
                    }
                    Task task;
                    if (command == Command.TODO) {
                        task = new Todo(argument);
                    } else if (command == Command.DEADLINE) {
                        task = new Deadline(argument);
                    } else {
                        task = new Event(argument);
                    }
                    arisUi.format(task.addTask(list));
                    break;

                case BYE: // exit program
                    arisUi.exit();
                    return;

                case UNKNOWN:
                default: // any other text
                    arisUi.format("Sorry forgot to code this bit ┐(´ー｀)┌");
            }
            try {
                saveFile();
            } catch (IOException e) {
                arisUi.format("Something went wrong ┐(´ー｀)┌");
            }
        }
    }

    private void loadFile() throws FileNotFoundException {
        File f = new File("./data/Aris.txt");
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

    private void saveFile() throws IOException {
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
