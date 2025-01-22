public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String status() {
        return isDone ? ("[X] " + description) : ("[ ] " + description);
    }

    public String markDone() {
        this.isDone = true;
        return "YAYYY :D You've done this task:\n" + this.status();
    }

    public String markUndone() {
        this.isDone = false;
        return "Oh wow >:( Go finish this up:\n" + this.status();
    }
}
