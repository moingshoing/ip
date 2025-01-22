import java.io.IOException;
import java.util.Scanner;

public class Aris {
    public static void main(String[] args) {
        UI Ui = new UI(); // UI for format messages
        Ui.greet(); // greet

        Task[] list = new Task[100];
        int count = 0;

        Scanner userInput =  new Scanner(System.in); // read user input

        while(true) {
            String input =  userInput.nextLine();

            String[] parts = input.split(" ", 2); // split command and argument
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";

            switch(command) { // use of switch because else if is ugly
                case "list":
                    StringBuilder s = new StringBuilder(); // using StringBuilder for efficient code (tbh doesn't affect much)
                    for (int i = 1; i < count + 1; i++) {
                        Task item = list[i - 1];
                        s.append(i).append(".").append(item.status());
                        if (i < count) { // line break except for last item; for formatting purposes; not much of an impact
                            s.append("\n");
                        }
                    }
                    Ui.format(s.toString());
                    break;

                case "mark":
                case "unmark":
                    try {
                        int index = Integer.parseInt(argument);
                        if (index <= 0 || index > count) { // number out of range of list
                            Ui.format("Number is out of range ┐(´ー｀)┌");
                            break;
                        }
                        if (command.equals("unmark")) {
                            Ui.format(list[index - 1].markUndone());
                        } else {
                            Ui.format(list[index - 1].markDone());
                        }
                    } catch (NumberFormatException e) { // number is not entered after mark/unmark
                        Ui.format("This is not a number ┐(´ー｀)┌");
                    }
                    break;
                case "todo":
                case "deadline":
                case "event":
                    if (argument.isEmpty()) {
                        Ui.format("Try doing something instead ┐(´ー｀)┌");
                        break;
                    }
                    Task task;
                    if (command.equals("todo")) {
                        task = new Todo(argument);
                    } else if (command.equals("deadline")) {
                        task = new Deadline(argument);
                    } else {
                        task = new Event(argument);
                    }
                    Ui.format(task.addTask(list, count));
                    count++;
                    break;
                case "bye":
                    Ui.exit();
                    return;
                default:
                    Ui.format("Sorry forgot to code this bit ┐(´ー｀)┌");
            }
        }
    }
}
