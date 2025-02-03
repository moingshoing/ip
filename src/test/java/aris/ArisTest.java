package aris;

import aris.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ArisTest {
    @Test
    public void testFileNotFound() {
        Aris aris = new Aris("./data/nonexistent.txt"); // This should trigger "No file found"

        String expectedMessage = "No file found ¯\\_(._.)_/¯\n";
        String actualMessage = aris.arisUi.getLastMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testMarkTask() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Aris aris = new Aris("./data/testfile.txt");
        aris.list.addTask(new Todo("Test Task"));
        aris.list.markTaskDone(1);

        String expected = "Nice! I've marked this task as done:\n[X] Test Task\n";
        String actual = output.toString();

        assertEquals(expected, actual);
    }
}
