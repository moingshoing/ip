package aris.command;

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
    UNKNOWN; // unknown command

    public static Command convert(String s) {
        try {
            return Command.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
