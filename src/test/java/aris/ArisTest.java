package aris;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aris.list.TaskList;
import aris.storage.Storage;
import aris.task.Deadline;
import aris.task.Event;
import aris.task.Todo;
import aris.ui.Ui;

public class ArisTest {
    protected Ui arisUi;
    protected TaskList list; // use of arraylist to store tasks
    protected Storage storage;

    @BeforeEach
    public void run() {
        arisUi = new Ui();
        storage = new Storage("./data/test.txt");
        list = new TaskList();
        list.addTask(new Todo("Test Todo"));
        list.addTask(new Deadline("Test Deadline /by 2019-10-10"));
        list.addTask(new Event("Test Event /from 2025-10-12 /to 2025-10-13"));
    }

    @Test
    public void testAddTasks() {
        String expected = "1.[T][ ] Test Todo\n"
                + "2.[D][ ] Test Deadline (by: Oct 10 2019)\n"
                + "3.[E][ ] Test Event (from: Oct 12 2025 to: Oct 13 2025)";
        String actual = list.printList();
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteTask() {
        list.deleteTask(2);
        String expected = "1.[T][ ] Test Todo\n"
                + "2.[E][ ] Test Event (from: Oct 12 2025 to: Oct 13 2025)";
        String actual = list.printList();
        assertEquals(expected, actual);
    }

    @Test
    public void testMarkTask() {
        list.markTaskDone(2);
        String expected = "1.[T][ ] Test Todo\n"
                + "2.[D][X] Test Deadline (by: Oct 10 2019)\n"
                + "3.[E][ ] Test Event (from: Oct 12 2025 to: Oct 13 2025)";
        String actual = list.printList();
        assertEquals(expected, actual);
    }

    @Test
    public void testUnmarkTask() {
        list.markTaskDone(2);
        list.markTaskUndone(2);
        String expected = "1.[T][ ] Test Todo\n"
                + "2.[D][ ] Test Deadline (by: Oct 10 2019)\n"
                + "3.[E][ ] Test Event (from: Oct 12 2025 to: Oct 13 2025)";
        String actual = list.printList();
        assertEquals(expected, actual);
    }

    @Test
    public void testIndexOutOfRange() {
        String actual = list.markTaskDone(4);
        String expected = "Number is out of range ~_~";
        assertEquals(expected, actual);
    }
}
