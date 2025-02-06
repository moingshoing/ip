package aris.task;

import java.util.ArrayList;

/**
 * Represents a general task with basic functionalities.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Adds this task to the provided list.
     * @param list The list of tasks.
     * @return A confirmation message.
     */
    public String addTask(ArrayList<Task> list) {
        list.add(this);
        // add task to duke.txt
        return String.format("Okei this task is added:\n%s\nYou now have %d task(s) in the list",
                status(), list.size());
    }

    /**
     * Deletes this task from the provided list.
     * @param list The list of tasks.
     * @param index The index of the task to delete.
     * @return A confirmation message.
     */
    public String delTask(ArrayList<Task> list, int index) {
        // del task from duke.txt
        list.remove(index - 1);
        return String.format("Okei this task is deleted:\n%s\nYou now have %d task(s) in the list",
                status(), list.size());
    }

    /**
     * Returns the status of the task.
     * @return A formatted string representing the task status.
     */
    public String status() {
        return isDone ? ("[X] " + description) : ("[ ] " + description);
    }

    /**
     * Formats the task for file storage.
     * @return A formatted string representing the task for storage.
     */
    public String fileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Marks the task as done.
     * @return A confirmation message.
     */
    public String markDone() {
        this.isDone = true;
        return "YAYYY :D You've done this task:\n" + this.status();
    }

    /**
     * Marks the task as undone.
     * @return A confirmation message.
     */
    public String markUndone() {
        this.isDone = false;
        return "Oh wow >:( Go finish this up:\n" + this.status();
    }

    /**
     * Checks if the task description contains the given keyword.
     * @param k The keyword to search for.
     * @return True if the description contains the keyword, otherwise false.
     */
    public boolean containsKeyword(String k) {
        String keyword = k.toLowerCase();
        return this.description.contains(keyword);
    }
}
