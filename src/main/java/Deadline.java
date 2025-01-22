public class Deadline extends Task {
    protected String deadline;
    public Deadline(String description) {
        String[] details = description.split("/by ", 2);
        this.description = details[0];
        this.deadline = String.format(" (by: %s)", details[1]);
        this.isDone = false;
    }

    public String status() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + description + deadline;
    }
}
