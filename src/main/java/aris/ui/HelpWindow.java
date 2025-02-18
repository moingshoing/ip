package aris.ui;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class HelpWindow {
    @FXML
    private TextArea helpText;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        helpText.setText("""
        list → View all tasks
        todo X → Add a todo task
        deadline X /by DATE → Add a deadline task
        event X /from DATE /to DATE → Add an event task
        mark N → Mark task N as done
        unmark N → Mark task N as not done
        delete N → Delete task N
        find X → Search tasks containing X
        bye → Exit the application
        help → Show this help page
        suisei -> Send a random Suisei video
        """);
    }

    @FXML
    private void handleClose() {
        if (stage != null) {
            stage.close();
        }
    }
}
