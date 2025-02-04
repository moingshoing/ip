package aris.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end period.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    protected String periodString;

    /**
     * Constructs an Event task.
     * @param description The task description.
     * @param doneInt 1 if completed, 0 otherwise.
     * @param from The start time.
     * @param to The end time.
     */
    public Event(String description, int doneInt, String from, String to) {
        this.description = description;
        this.from = dateFormatter(from);
        this.to = dateFormatter(to);
        this.periodString = String.format(" (from: %s to: %s)", this.from, this.to);
        this.isDone = (doneInt != 0);
    }

    /**
     * Constructs an Event task from a description string.
     * @param description The task description with a start time and an end time.
     */
    public Event(String description) {
        String[] details = description.split(" /from ", 2);
        this.description = details[0];
        String[] period = details[1].split(" /to ", 2);
        this.from = dateFormatter(period[0]);
        this.to = dateFormatter(period[1]);
        this.periodString = String.format(" (from: %s to: %s)", this.from, this.to);
        this.isDone = false;
    }

    @Override
    public String status() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + description + periodString;
    }

    @Override
    public String fileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + String.format(" | %s-%s", from, to);
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
