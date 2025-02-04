package aris.command;

/**
 * Represents a command that can be issued to the chatbot.
 */
public enum Command {
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    BYE,
    UNKNOWN; // unknown command

    /**
     * Converts a string to its corresponding Command enum.
     * @param s The input string.
     * @return The corresponding Command, or UNKNOWN if invalid.
     */
    public static Command convert(String s) {
        try {
            return Command.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
