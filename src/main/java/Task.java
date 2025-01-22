public class Task {
    protected String description;
    protected boolean isDone;

    public String addTask(Task[] list, int count) {
        list[count] = this;
        return String.format("Okei task added :>\n%s\nYou now have %d task(s) in the list", status(), count + 1);
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
