public class Task {
    protected String description;
    protected boolean isDone;

    public void addTask(Task[] list, int count) {
        list[count] = this;
        System.out.println("Okie task added :>");
        System.out.println(status());
        System.out.println("You now have " + (count + 1) + " task(s) in the list");
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
