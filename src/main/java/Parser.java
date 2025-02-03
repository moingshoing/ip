public class Parser {

    public static Command parseCommand(String input) {
        String[] parts = input.split(" ", 2); // split command and argument
        String commandStr = parts[0];
        return Command.convert(commandStr); // convert to enum
    }

    public static String parseArgument(String input) {
        String[] parts = input.split(" ", 2); // split command and argument
        return parts.length > 1 ? parts[1] : ""; // return argument if present
    }
}
