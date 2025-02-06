package aris.ui;

/**
 * Handles user interaction and formatting of system messages.
 */
public class Ui {
    private String hline = "____________________________________________________________\n";

    /**
     * Formats and prints a message with a horizontal line.
     * @param s The message to format and print.
     */
    public void format(String s) {
        System.out.println(hline + s + "\n");
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        format("Hello! I'm Aris!\n|･ω･｀) < hi.");
    }

    /**
     * Displays an exit message before the application terminates.
     */
    public void exit() {
        format("Time to go to bed (-_-) zzZ");
    }
}
