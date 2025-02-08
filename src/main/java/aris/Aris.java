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
    protected Ui arisUi;
    protected TaskList list; // use of arraylist to store tasks
    protected Storage storage;
    private String commandType;
    private Scanner userInput = new Scanner(System.in); //scanner to read input

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
            arisUi.format("No file found (ㆆ_ㆆ)");
        } catch (IllegalArgumentException e) {
            arisUi.format("File corrupted (ㆆ_ㆆ)");
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command c = Parser.parseCommand(input);
        String arg = Parser.parseArgument(input);
        commandType = Command.getSimpleName(c);
        return execute(c, arg);
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
        String reply;
        switch(command) {
        case FIND:
            reply = arisUi.format(list.findTask(argument));
            break;

        case LIST:
            reply = arisUi.format(list.printList());
            break;

        case MARK:
            // Fallthrough
        case UNMARK:
            // Fallthrough
        case DELETE:
            try {
                int index = Integer.parseInt(argument);
                if (command == Command.UNMARK) {
                    reply = arisUi.format(list.markTaskUndone(index));
                } else if (command == Command.MARK) {
                    reply = arisUi.format(list.markTaskDone(index));
                } else {
                    reply = arisUi.format(list.deleteTask(index));
                }
            } catch (NumberFormatException e) { // number is not entered after mark/unmark
                reply = arisUi.format("This is not a number (ㆆ_ㆆ)");
            }
            break;

        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            if (argument.isEmpty()) { // empty argument
                reply = arisUi.format("Try doing something instead (ㆆ_ㆆ)");
                break;
            }
            Task task;
            if (command == Command.TODO) {
                task = new Todo(argument);
            } else if (command == Command.DEADLINE) {
                task = new Deadline(argument);
            } else {
                task = new Event(argument);
            }
            reply = arisUi.format(list.addTask(task));
            break;

        case BYE: // Exit program
            reply = arisUi.exit();

            // delay exit by 2 seconds
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();

            break;

        case UNKNOWN:
            // Fallthrough
        default: // Any other text
            reply = arisUi.format("Sorry forgot to code this bit (ㆆ_ㆆ)");
        }
        try {
            storage.saveFile(list);
        } catch (IOException e) {
            reply = arisUi.format("Something went wrong (ㆆ_ㆆ)");
        }
        return reply;
    }
}
