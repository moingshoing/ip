package aris.ui;

/**
 * Handles user interaction and formatting of system messages.
 */
public class Ui {
    /**
     * Formats and prints a message with a horizontal line.
     * @param s The message to format and print.
     */
    public String format(String s) {
        return s + "\n";
    }

    /**
     * Displays a greeting message to the user.
     */
    public String greet() {
        return format("Hello! I'm Aris!\n" +
                "|･ω･｀) < hi.\n" +
                "Lost? Just type 'help' to get started!");
    }

    /**
     * Displays an exit message before the application terminates.
     */
    public String exit() {
        return format("Time to go to bed (-_-) zzZ");
    }
}
