import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public String printList() {
        StringBuilder s = new StringBuilder(); // StringBuilder for efficiency
        int i = 1;
        for (Iterator<Task> it = list.iterator(); it.hasNext(); i++) {
            Task item = it.next();
            s.append(i).append(".").append(item.status());
            if (it.hasNext()) { // line break except for last item; for formatting purposes
                s.append("\n");
            }
        }
        return s.toString();
    }

    public String toFile() {
        StringBuilder s = new StringBuilder(); // StringBuilder for efficiency
        int i = 1;
        for (Iterator<Task> it = list.iterator(); it.hasNext(); i++) {
            Task item = it.next();
            s.append(item.fileFormat());
            if (it.hasNext()) { // line break except for last item; for formatting purposes
                s.append("\n");
            }
        }
        return s.toString();
    }

    public void isIndexValid(int index) throws IllegalArgumentException {
        if (index <= 0 || index > list.size()) { // number out of range of list/ empty arg
            throw new IllegalArgumentException();
        }
    }

    public String markTaskDone(int index) {
        try {
            isIndexValid(index);
            return list.get(index - 1).markDone();
        } catch (IllegalArgumentException e) {
            return "Number is out of range ┐(´ー｀)┌";
        }
    }

    public String markTaskUndone(int index) {
        try {
            isIndexValid(index);
            return list.get(index - 1).markUndone();
        } catch (IllegalArgumentException e) {
            return "Number is out of range ┐(´ー｀)┌";
        }
    }

    public String deleteTask(int index) {
        try {
            Task task = list.get(index - 1); // a little roundabout, might fix in the future
            return task.delTask(list, index);
        } catch (IllegalArgumentException e) {
            return "Number is out of range ┐(´ー｀)┌";
        }
    }

    public String addTask(Task task) {
        return task.addTask(list);
    }
}
