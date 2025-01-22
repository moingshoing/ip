public class Todo extends Task {
    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String status() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + description;
    }
}
