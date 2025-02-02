import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String deadline;
    protected String deadlineString;

    public Deadline(String description, int doneInt, String deadline) {
        this.description = description;
        this.deadline = deadline;
        try {
            this.deadline = dateFormatter();
        } catch (DateTimeParseException ignored) {
        } finally {
            this.deadlineString = String.format(" (by: %s)", deadline);
        }
        this.isDone = (doneInt != 0);
    }

    public Deadline(String description) {
        String[] details = description.split(" /by ", 2);
        this.description = details[0];
        this.deadline = details[1];
        try {
            this.deadline = dateFormatter();
        } catch (DateTimeParseException ignored) {
        } finally {
            this.deadlineString = String.format(" (by: %s)", deadline);
        }
        this.isDone = false;
    }

    @Override
    public String status() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + description + deadlineString;
    }

    @Override
    public String fileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + String.format(" | %s", deadline);
    }

    public String dateFormatter() throws DateTimeParseException {
        LocalDate date = LocalDate.parse(deadline);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
