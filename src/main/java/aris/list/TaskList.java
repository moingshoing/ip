package aris.list;

import java.util.ArrayList;
import java.util.Iterator;

import aris.task.Task;

/**
 * Represents a list of tasks and provides methods for manipulating tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Returns a formatted string representation of all tasks in the list.
     * @return A string representation of the task list.
     */
    public String printList() {
        StringBuilder s = new StringBuilder(); // StringBuilder for efficiency
        int i = 1;
        for (Iterator<Task> it = list.iterator(); it.hasNext(); i++) {
            Task item = it.next();
            s.append(i).append(".").append(item.status());
            if (it.hasNext()) { // line break except for last item; for formatting purposes
                s.append("\n");
            }
        }
        return s.toString();
    }

    /**
     * Converts the list of tasks into a file-friendly format.
     * @return A formatted string representing the tasks for storage.
     */
    public String toFile() {
        StringBuilder s = new StringBuilder(); // StringBuilder for efficiency
        int i = 1;
        for (Iterator<Task> it = list.iterator(); it.hasNext(); i++) {
            Task item = it.next();
            s.append(item.fileFormat());
            if (it.hasNext()) { // line break except for last item; for formatting purposes
                s.append("\n");
            }
        }
        return s.toString();
    }

    /**
     * Checks if the given index is valid for accessing the task list.
     * @param index The index to check.
     * @throws IllegalArgumentException If the index is out of bounds.
     */
    public void isIndexValid(int index) throws IllegalArgumentException {
        if (index <= 0 || index > list.size()) { // number out of range of list/ empty arg
            throw new IllegalArgumentException();
        }
    }

    /**
     * Marks a task as done at the specified index.
     * @param index The index of the task to mark as done.
     * @return A confirmation message.
     */
    public String markTaskDone(int index) {
        try {
            isIndexValid(index);
            return list.get(index - 1).markDone();
        } catch (IllegalArgumentException e) {
            return "Number is out of range ¯\\_(._.)_/¯";
        }
    }

    /**
     * Marks a task as undone at the specified index.
     * @param index The index of the task to mark as undone.
     * @return A confirmation message.
     */
    public String markTaskUndone(int index) {
        try {
            isIndexValid(index);
            return list.get(index - 1).markUndone();
        } catch (IllegalArgumentException e) {
            return "Number is out of range ¯\\_(._.)_/¯";
        }
    }

    /**
     * Deletes a task at the specified index.
     * @param index The index of the task to delete.
     * @return A confirmation message.
     */
    public String deleteTask(int index) {
        try {
            Task task = list.get(index - 1); // a little roundabout, might fix in the future
            return task.delTask(list, index);
        } catch (IllegalArgumentException e) {
            return "Number is out of range ¯\\_(._.)_/¯";
        }
    }

    /**
     * Adds a new task to the list.
     * @param task The task to add.
     * @return A confirmation message.
     */
    public String addTask(Task task) {
        return task.addTask(list);
    }

    /**
     * Finds tasks that contain the given keyword.
     * @param keyword The keyword to search for within the task descriptions.
     * @return A formatted string of tasks that contain the keyword, or a message if no results are found.
     */
    public String findTask(String keyword) {
        StringBuilder s = new StringBuilder(); // StringBuilder for efficiency
        int i = 1;
        for (Iterator<Task> it = list.iterator(); it.hasNext(); i++) {
            Task item = it.next();
            if (item.containsKeyword(keyword)) {
                s.append(i).append(".").append(item.status());
                if (it.hasNext()) {
                    s.append("\n");
                }
            }
        }

        if (s.toString().isEmpty()) {
            return "No results found ¯\\_(._.)_/¯";
        }
        return s.toString();
    }
}
