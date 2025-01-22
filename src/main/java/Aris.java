import java.util.Scanner;

public class Aris {
    public static void main(String[] args) {
        UI Ui = new UI(); // UI for format messages
        Ui.greet(); // greet

        Task[] list = new Task[100];
        int count = 0;

        Scanner userInput =  new Scanner(System.in); // read user input
        String input =  userInput.nextLine();

        while (!input.equals("bye")) { // exit the program when user types bye
            if (input.equals("list")) {
                String s = "";
                for (int i = 1; i < count + 1; i++) {
                    Task item = list[i - 1];
                    s += i + "." + item.status() + "\n";
                }
                Ui.format(s);
                input = userInput.nextLine();
                continue;
            }

            if (input.contains("unmark")) {
                try {
                    int index = Integer.parseInt(input.split(" ", 2)[1]);
                    String status = list[index - 1].markUndone();
                    Ui.format(status);
                } catch (NumberFormatException e) {
                    System.out.println("error");
                }
                input = userInput.nextLine();
                continue;
            }

            if (input.contains("mark")) {
                try {
                    int index = Integer.parseInt(input.split(" ", 2)[1]);
                    String status = list[index - 1].markDone();
                    Ui.format(status);
                } catch (NumberFormatException e) {
                    System.out.println("error");
                }
                input = userInput.nextLine();
                continue;
            }

            if (input.contains("todo")) {
                String str = input.split(" ", 2)[1];
                Task task = new Todo(str);
                task.addTask(list, count);
                count++;
                input = userInput.nextLine();
                continue;
            }

            if (input.contains("deadline")) {
                String str = input.split(" ", 2)[1];
                Task task = new Deadline(str);
                task.addTask(list, count);
                count++;
                input = userInput.nextLine();
                continue;
            }

            if (input.contains("event")) {
                String str = input.split(" ", 2)[1];
                Task task = new Event(str);
                task.addTask(list, count);
                count++;
                input = userInput.nextLine();
                continue;
            }

            Ui.format(input);
            input = userInput.nextLine();
        }

        Ui.exit(); // exit
    }
}
