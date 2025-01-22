public class UI {
    public String hline = "____________________________________________________________\n";

    public void format(String s) {
        System.out.println(hline + s + "\n" + hline);
    }

    public void greet() {
        format("Panpakapan! I'm Aris!\n|･ω･｀) < hi.");
    }

    public void exit() {
        format("Time to go to bed (－_－) zzZ");
    }
}
