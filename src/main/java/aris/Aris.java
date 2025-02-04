package aris;

import aris.command.Command;
import aris.list.TaskList;
import aris.parser.Parser;
import aris.storage.Storage;
import aris.task.Deadline;
import aris.task.Event;
import aris.task.Task;
import aris.task.Todo;
import aris.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The main class for the Aris chatbot application.
 * It manages tasks and user interactions.
 */
public class Aris {
    protected Ui arisUi;
    protected TaskList list; // use of arraylist to store tasks
    protected Storage storage;
    Scanner userInput =  new Scanner(System.in); // scanner to read input

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
        } catch(FileNotFoundException e) {
            arisUi.format("No file found ¯\\_(._.)_/¯");
        }
    }

    public static void main(String[] args) {
        Aris aris = new Aris("./data/Aris.txt");
        aris.run();
    }

    /**
     * Runs the chatbot loop, handling user input and executing commands.
     */
    void run() {
        Ui arisUi = new Ui(); // UI for format messages
        arisUi.greet(); // greet

        while(true) {
            String input =  userInput.nextLine();
            Command command = Parser.parseCommand(input);
            String argument = Parser.parseArgument(input);

            switch(command) { // use of switch because else if is ugly
                case LIST:
                    arisUi.format(list.printList());
                    break;

                case MARK:
                case UNMARK:
                case DELETE:
                    try {
                        int index = Integer.parseInt(argument);
                        if (command == Command.UNMARK) {
                            arisUi.format(list.markTaskUndone(index));
                        } else if (command == Command.MARK) {
                            arisUi.format(list.markTaskDone(index));
                        } else {
                            arisUi.format(list.deleteTask(index));
                        }
                    } catch (NumberFormatException e) { // number is not entered after mark/unmark
                        arisUi.format("This is not a number ¯\\_(._.)_/¯");
                    }
                    break;

                case TODO:
                case DEADLINE:
                case EVENT:
                    if (argument.isEmpty()) { // empty argument
                        arisUi.format("Try doing something instead ¯\\_(._.)_/¯");
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
                    arisUi.format(list.addTask(task));
                    break;

                case BYE: // exit program
                    arisUi.exit();
                    return;

                case UNKNOWN:
                default: // any other text
                    arisUi.format("Sorry forgot to code this bit ¯\\_(._.)_/¯");
            }
            try {
                storage.saveFile(list);
            } catch (IOException e) {
                arisUi.format("Something went wrong ¯\\_(._.)_/¯");
            }
        }
    }
}
