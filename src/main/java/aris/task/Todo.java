package aris.task;

/**
 * Represents a to-do task without any date/time attached.
 */
public class Todo extends Task {
    /**
     * Constructs a new to-do task.
     * @param description The task description.
     */
    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new to-do task with completion status.
     * @param description The task description.
     * @param doneInt 1 if the task is done, 0 otherwise.
     */
    public Todo(String description, int doneInt) {
        this.description = description;
        this.isDone = (doneInt != 0);
    }

    @Override
    public String status() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + description;
    }
}
