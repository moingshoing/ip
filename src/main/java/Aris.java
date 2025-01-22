import java.util.Scanner;

public class Aris {
    public static void main(String[] args) {
        UI Ui = new UI(); // UI for format messages
        Ui.greet(); // greet

        Scanner userInput =  new Scanner(System.in); // read user input
        String input =  userInput.nextLine();

        while (!input.equals("bye")) { // exit the program when user types bye
            Ui.format(input);
            input = userInput.nextLine();
        }

        Ui.exit(); // exit
    }
}
