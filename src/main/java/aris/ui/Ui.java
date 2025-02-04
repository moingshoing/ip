package aris.ui;

public class Ui {
    private String hline = "____________________________________________________________\n";
    private String lastMessage = "";

    public void format(String s) {
        lastMessage = s;
        System.out.println(hline + s + "\n");
    }

    public void greet() {
        format("Panpakapan! I'm aris.Aris!\n|･ω･｀) < hi.");
    }

    public void exit() {
        format("Time to go to bed (-_-) zzZ");
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
