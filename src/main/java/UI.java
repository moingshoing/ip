public class UI {
    public String hline = "____________________________________________________________\n";

    public void format(String s) {
        System.out.println(hline + s + "\n" + hline);
    }

    public void greet() {
        format("Panpakapan! I'm Aris!\nWhat can I do for you?");
    }

    public void exit() {
        format("NOOOOOO COME BACK PLEASE :(");
    }
}
