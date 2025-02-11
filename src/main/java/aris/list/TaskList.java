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
        assert list != null : "Task list should not be null";
        StringBuilder s = new StringBuilder(); // StringBuilder for efficiency
        int i = 1;
        for (Iterator<Task> it = list.iterator(); it.hasNext(); i++) {
            Task item = it.next();
            assert item != null : "Task should not be null";
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
        assert list != null : "Task list should not be null";
        StringBuilder s = new StringBuilder(); // StringBuilder for efficiency
        int i = 1;
        for (Iterator<Task> it = list.iterator(); it.hasNext(); i++) {
            Task item = it.next();
            assert item != null : "Task should not be null";
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
        assert list != null : "Task list should not be null";
        try {
            isIndexValid(index);
            assert list.get(index - 1) != null : "Task at index should not be null";
            return list.get(index - 1).markDone();
        } catch (IllegalArgumentException e) {
            return "Number is out of range (ㆆ_ㆆ)";
        }
    }

    /**
     * Marks a task as undone at the specified index.
     * @param index The index of the task to mark as undone.
     * @return A confirmation message.
     */
    public String markTaskUndone(int index) {
        assert list != null : "Task list should not be null";
        try {
            isIndexValid(index);
            assert list.get(index - 1) != null : "Task at index should not be null";
            return list.get(index - 1).markUndone();
        } catch (IllegalArgumentException e) {
            return "Number is out of range (ㆆ_ㆆ)";
        }
    }

    /**
     * Deletes a task at the specified index.
     * @param index The index of the task to delete.
     * @return A confirmation message.
     */
    public String deleteTask(int index) {
        assert list != null : "Task list should not be null";
        try {
            isIndexValid(index);
            Task task = list.get(index - 1);
            assert task != null : "Task to be deleted should not be null";
            return task.delTask(list, index);
        } catch (IllegalArgumentException e) {
            return "Number is out of range (ㆆ_ㆆ)";
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
        assert list != null : "Task list should not be null";
        assert keyword != null : "Keyword should not be null";
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
            return "No results found (ㆆ_ㆆ)";
        }
        return s.toString();
    }
}
