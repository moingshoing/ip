public class Todo extends Task {
    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Todo(String description, int doneInt) {
        this.description = description;
        this.isDone = (doneInt != 0);
    }

    @Override
    public String status() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + description;
    }
}
