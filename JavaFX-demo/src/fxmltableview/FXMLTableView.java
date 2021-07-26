package fxmltableview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 */
public class FXMLTableView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane myPane = FXMLLoader.load(getClass().getResource("FXMLTableView.fxml"));
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.setTitle("FXML TableView Example");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
