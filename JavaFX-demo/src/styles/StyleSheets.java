package styles;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 *
 */
public class StyleSheets extends Application {

    Label lblOutput;

    @Override
    public void start(Stage primaryStage) {

        Label lbl = new Label("Press the button to see a message");
        lblOutput = new Label(null);
        lblOutput.setId("textstyle");

        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(event -> lblOutput.setText("Hello World!"));

        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);
        root.setOrientation(Orientation.VERTICAL);
        root.getChildren().addAll(lbl, btn, lblOutput);

        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add("styles/style.css");

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
