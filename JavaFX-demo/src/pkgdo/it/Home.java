package pkgdo.it;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Home extends Application {

    public static final String FILE = "tasks.xml";
    private Controller controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui.fxml"));

        GridPane grid = loader.load(); // 先にloadする
        controller = loader.getController();

        Scene scene = new Scene(grid, 600, 400);

        stage.setScene(scene);
        stage.setTitle("Do-It!!!");
        stage.setAlwaysOnTop(false);
        stage.setResizable(false);

        stage.setOnCloseRequest(this::onClose);
        controller.setTasksMap(readTasksFile());
        stage.show();
    }

    private HashMap<Integer, Task> readTasksFile() {
        try (FileInputStream in = new FileInputStream(FILE);
             XMLDecoder decoder = new XMLDecoder(in)) {
            return (HashMap<Integer, Task>) decoder.readObject();
        } catch (IOException ignore) {
            System.out.println("File not found. So creating it.");
            return new HashMap<>();
        }
    }

    private void onClose(WindowEvent event) {
        try (FileOutputStream out = new FileOutputStream(FILE);
             XMLEncoder encoder = new XMLEncoder(out)) {
            encoder.writeObject(controller.getTasksMap());
        } catch (IOException ignore) {
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
