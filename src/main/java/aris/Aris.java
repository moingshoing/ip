package aris;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import aris.command.Command;
import aris.list.TaskList;
import aris.parser.Parser;
import aris.storage.Storage;
import aris.task.Deadline;
import aris.task.Event;
import aris.task.Task;
import aris.task.Todo;
import aris.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * The main class for the Aris chatbot application.
 * It manages tasks and user interactions.
 */
public class Aris {
    private static final String ERROR_NUMBER = "This is not a number (ㆆ_ㆆ)";
    private static final String ERROR_UNKNOWN = "Sorry forgot to code this bit (ㆆ_ㆆ)";
    private static final String ERROR_GENERIC = "Something went wrong (ㆆ_ㆆ)";
    private static final String ERROR_NO_FILE = "No file found (ㆆ_ㆆ)";
    private static final String ERROR_CORRUPTED_FILE = "File corrupted (ㆆ_ㆆ)";
    private static final String ERROR_EMPTY_ARGUMENT = "Try doing something instead (ㆆ_ㆆ)";
    private static final String GREETING_MESSAGE = "|･ω･｀) < hello.";

    protected Ui arisUi;
    protected TaskList list; // use of arraylist to store tasks
    protected Storage storage;
    private String commandType;

    /**
     * Constructs an Aris instance with the specified file path.
     * @param filePath The file path for storing tasks.
     */
    public Aris(String filePath) {
        this.list = new TaskList();
        this.storage = new Storage(filePath);
        this.arisUi = new Ui();
        try {
            storage.loadFile(list);
        } catch (FileNotFoundException e) {
            arisUi.format(ERROR_NO_FILE);
        } catch (IllegalArgumentException e) {
            arisUi.format(ERROR_CORRUPTED_FILE);
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command command = Parser.parseCommand(input);
        String argument = Parser.parseArgument(input);
        commandType = Command.getSimpleName(command);
        return execute(command, argument);
    }

    /**
     * Retrieves the type of command being executed.
     * @return The type of the command as a string.
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Executes the given command with the provided argument.
     * @param command The command to execute.
     * @param argument The argument associated with the command.
     * @return A formatted response based on the command execution.
     */
    public String execute(Command command, String argument) {
        String reply = switch (command) {
            case FIND -> arisUi.format(list.findTask(argument));
            case LIST -> arisUi.format(list.printList());
            case MARK, UNMARK, DELETE -> handleTaskModification(command, argument);
            case TODO, DEADLINE, EVENT -> handleTaskCreation(command, argument);
            case BYE -> handleExit();
            case GREET -> arisUi.format(GREETING_MESSAGE);
            default -> arisUi.format(ERROR_UNKNOWN);
        };
        return saveAndReturnReply(reply);
    }

    /**
     * Handles marking, unmarking, and deleting tasks.
     */
    private String handleTaskModification(Command command, String argument) {
        try {
            int index = Integer.parseInt(argument);
            return switch (command) {
                case UNMARK -> arisUi.format(list.markTaskUndone(index));
                case MARK -> arisUi.format(list.markTaskDone(index));
                case DELETE -> arisUi.format(list.deleteTask(index));
                default -> throw new IllegalStateException("Unexpected value: " + command);
            };
        } catch (NumberFormatException e) {
            return arisUi.format(ERROR_NUMBER);
        }
    }

    /**
     * Handles adding TODO, DEADLINE, and EVENT tasks.
     */
    private String handleTaskCreation(Command command, String argument) {
        if (argument.isEmpty()) {
            return arisUi.format(ERROR_EMPTY_ARGUMENT);
        }
        Task task = switch (command) {
            case TODO -> new Todo(argument);
            case DEADLINE -> new Deadline(argument);
            case EVENT -> new Event(argument);
            default -> throw new IllegalStateException("Unexpected value: " + command);
        };
        return arisUi.format(list.addTask(task));
    }

    /**
     * Handles the exit process with a delay.
     */
    private String handleExit() {
        String reply = arisUi.exit();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
        return reply;
    }

    /**
     * Saves the task list to storage and returns the reply.
     */
    private String saveAndReturnReply(String reply) {
        try {
            storage.saveFile(list);
        } catch (IOException e) {
            return arisUi.format(ERROR_GENERIC);
        }
        return reply;
    }
}
