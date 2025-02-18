package aris.command;

/**
 * Represents a command that can be issued to the chatbot.
 */
public enum Command {
    FIND,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    BYE,
    GREET,
    HELP,
    PUNCH,
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

    public static String getSimpleName(Command c) {
        switch (c) {
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            return "AddCommand";
        case MARK:
            // Fallthrough
        case UNMARK:
            return "ChangeMarkCommand";
        case DELETE:
            return "DeleteCommand";
        case FIND:
            return "FindCommand";
        default:
            return "OtherCommand";
        }
    }
}
