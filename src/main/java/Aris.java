import java.util.Scanner;

public class Aris {
    public static void main(String[] args) {
        String hline = "____________________________________________________________\n";
        String greet = "Panpakapan! I'm Aris! \nWhat can I do for you?\n";
        String exit = "NOOOOOO COME BACK PLEASE :(\n";
        System.out.println(hline + greet + hline); // greet

        Scanner userInput =  new Scanner(System.in); // read user input
        String input =  userInput.nextLine();

        while (!input.equals("bye")) { // exit the program when user types bye
            System.out.println(input + hline);
            input = userInput.nextLine();
        }

        System.out.println(exit + hline); // exit
    }
}
