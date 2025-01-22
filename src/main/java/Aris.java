import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Aris {
    public static void main(String[] args) {
        UI Ui = new UI(); // UI for format messages
        Ui.greet(); // greet

        ArrayList<Task> list = new ArrayList<Task>(); // use of arraylist to store tasks

        Scanner userInput =  new Scanner(System.in); // read user input

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
                    Ui.format(s.toString());
                    break;

                case MARK:
                case UNMARK:
                case DELETE:
                    try {
                        int index = Integer.parseInt(argument);
                        if (index <= 0 || index > list.size()) { // number out of range of list/ empty arg
                            Ui.format("Number is out of range :(");
                            break;
                        }
                        if (command == Command.UNMARK) {
                            Ui.format(list.get(index - 1).markUndone());
                        } else if (command == Command.MARK) {
                            Ui.format(list.get(index - 1).markDone());
                        } else {
                            Task task = list.get(index - 1); // a little roundabout, might fix in the future
                            Ui.format(task.delTask(list, index));
                        }
                    } catch (NumberFormatException e) { // number is not entered after mark/unmark
                        Ui.format("This is not a number :(");
                    }
                    break;

                case TODO:
                case DEADLINE:
                case EVENT:
                    if (argument.isEmpty()) { // empty argument
                        Ui.format("Try doing something instead ┐(´ー｀)┌");
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
                    Ui.format(task.addTask(list));
                    break;

                case BYE: // exit program
                    Ui.exit();
                    return;

                case UNKNOWN:
                default: // any other text
                    Ui.format("Sorry forgot to code this bit ┐(´ー｀)┌");
            }
        }
    }
}
