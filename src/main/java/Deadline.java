import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String deadline;
    protected String deadlineString;

    public Deadline(String description, int doneInt, String deadline) {
        this.description = description;
        this.deadline = dateFormatter(deadline);
        this.deadlineString = String.format(" (by: %s)", this.deadline);
        this.isDone = (doneInt != 0);
    }

    public Deadline(String description) {
        String[] details = description.split(" /by ", 2);
        this.description = details[0];
        this.deadline = dateFormatter(details[1]);
        this.deadlineString = String.format(" (by: %s)", this.deadline);
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

    public String dateFormatter(String deadline) {
        try {
            LocalDate date = LocalDate.parse(deadline);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return deadline;
        }
    }
}
