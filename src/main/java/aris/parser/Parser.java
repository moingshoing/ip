package aris.parser;

import java.util.Set;

import aris.command.Command;

/**
 * The Parser class processes user input and extracts commands and arguments.
 */
public class Parser {

    /**
     * Parses the command from a given input string.
     * @param input The user input.
     * @return The corresponding Command enum value.
     */
    public static Command parseCommand(String input) {
        String[] parts = input.split(" ", 2); // split command and argument
        String commandStr = parts[0];

        if (Set.of("hi", "hey", "hello").contains(commandStr.toLowerCase())) {
            return Command.convert("greet");
        }

        return Command.convert(commandStr); // convert to enum
    }

    /**
     * Extracts the argument from a given input string.
     * @param input The user input.
     * @return The argument part of the input, or an empty string if no argument exists.
     */
    public static String parseArgument(String input) {
        String[] parts = input.split(" ", 2); // split command and argument
        return parts.length > 1 ? parts[1] : ""; // return argument if present
    }
}
