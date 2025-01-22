import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    public String addTask(ArrayList<Task> list) {
        list.add(this);
        return String.format("Okei this task is added:\n%s\nYou now have %d task(s) in the list", status(), list.size());
    }

    public String delTask(ArrayList<Task> list, int index) {
        list.remove(index - 1);
        return String.format("Okei this task is deleted:\n%s\nYou now have %d task(s) in the list", status(), list.size());
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
