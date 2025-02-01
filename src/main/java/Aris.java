import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Aris {
    ArrayList<Task> list; // use of arraylist to store tasks
    Scanner userInput =  new Scanner(System.in); // scanner to read input

    private Aris() {
        this.list = new ArrayList<Task>();
    }

    public static void main(String[] args) {
        Aris aris = new Aris();
        aris.runAris();
    }

    private void runAris() {
        Ui arisUi = new Ui(); // UI for format messages
        arisUi.greet(); // greet

        try {
            // load todolist from file
            // method to load todolist?
        } catch(Exception e) {
            // no todolist, file is created from user input
        }

        while(true) {
            String input =  userInput.nextLine();

            String[] parts = input.split(" ", 2); // split command and argument
            String commandStr = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";

            Command command = Command.convert(commandStr); // convert input to enum

            switch(command) { // use of switch because else if is ugly
                case LIST:
                    StringBuilder s = new StringBuilder(); // StringBuilder for efficiency
                    int i = 1;
                    for (Iterator<Task> it = list.iterator(); it.hasNext(); i++) {
                        Task item = it.next();
                        s.append(i).append(".").append(item.status());
                        if (it.hasNext()) { // line break except for last item; for formatting purposes
                            s.append("\n");
                        }
                    }
                    arisUi.format(s.toString());
                    break;

                case MARK:
                case UNMARK:
                case DELETE:
                    try {
                        int index = Integer.parseInt(argument);
                        if (index <= 0 || index > list.size()) { // number out of range of list/ empty arg
                            arisUi.format("Number is out of range ┐(´ー｀)┌");
                            break;
                        }
                        if (command == Command.UNMARK) {
                            arisUi.format(list.get(index - 1).markUndone());
                        } else if (command == Command.MARK) {
                            arisUi.format(list.get(index - 1).markDone());
                        } else {
                            Task task = list.get(index - 1); // a little roundabout, might fix in the future
                            arisUi.format(task.delTask(list, index));
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
        }
    }

    private void loadTodolist(Ui arisUi) throws FileNotFoundException {
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
            arisUi.format(task.addTask(list));
        }
    }

    private void saveTodolist() {
        //
    }
}
