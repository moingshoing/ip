package aris;

import java.io.IOException;

import aris.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class initializes and starts the JavaFX application.
 */
public class Main extends Application {
    private Aris aris = new Aris(getHostServices(), "./data/Aris.txt");

    /**
     * Starts the JavaFX application by setting up the primary stage.
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setTitle("Aris");

            stage.setMinHeight(220);
            stage.setMinWidth(417);

            fxmlLoader.<MainWindow>getController().setAris(aris); // inject the Aris instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
