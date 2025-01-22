import java.util.Scanner;

public class Aris {
    public static void main(String[] args) {
        UI Ui = new UI(); // UI for format messages
        Ui.greet(); // greet

        String[] list = new String[100];
        int count = 0;

        Scanner userInput =  new Scanner(System.in); // read user input
        String input =  userInput.nextLine();

        while (!input.equals("bye")) { // exit the program when user types bye
            if (input.equals("list")) {
                String s = "";
                for (int i = 1; i < count + 1; i++) {
                    s += i + ". " + list[i - 1] + "\n";
                }
                Ui.format(s);
            } else {
                Ui.format("added: " + input);

                list[count] = input;
                count++;
            }

            input = userInput.nextLine();
        }

        Ui.exit(); // exit
    }
}
