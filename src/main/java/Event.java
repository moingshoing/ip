public class Event extends Task {
    protected String from;
    protected String to;
    protected String periodString;

    public Event(String description, int doneInt, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
        this.periodString = String.format(" (from: %s to: %s)", from, to);
        this.isDone = (doneInt != 0);
    }
    public Event(String description) {
        String[] details = description.split(" /from ", 2);
        this.description = details[0];
        String[] period = details[1].split(" /to ", 2);
        this.from = period[0];
        this.to = period[1];
        this.periodString = String.format(" (from: %s to: %s)", from, to);
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
}
