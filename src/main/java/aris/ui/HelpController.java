package aris.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class HelpController {
    @FXML
    private TextArea helpText;

    /**
     * Initializes the Help page.
     */
    @FXML
    public void initialize() {
        helpText.setText("""
                list      → View all tasks
                todo X    → Add a TODO task
                deadline X /by DATE → Add a deadline task
                event X /at DATE → Add an event task
                mark N    → Mark task N as done
                unmark N  → Mark task N as not done
                delete N  → Delete task N
                find X    → Search tasks containing X
                bye       → Exit the application
                help      → Show this help page
                """);
    }

    /**
     * Closes the Help window.
     */
    public void closeHelp() {
        Stage stage = (Stage) helpText.getScene().getWindow();
        stage.close();
    }
}
