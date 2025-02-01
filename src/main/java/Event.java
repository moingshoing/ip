public class Event extends Task {
    protected String period;

    public Event(String description, int doneInt, String from, String to) {
        this.description = description;
        this.period = String.format(" (from: %s to: %s)", from, to);
        this.isDone = false;
        this.isDone = (doneInt != 0);
    }
    public Event(String description) {
        String[] details = description.split("/from", 2);
        this.description = details[0];
        String[] period = details[1].split("/to", 2);
        this.period = String.format(" (from: %s to: %s)", period[0], period[1]);
        this.isDone = false;
    }

    public String status() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + description + period;
    }
}
