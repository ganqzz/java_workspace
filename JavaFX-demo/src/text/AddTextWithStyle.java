package text;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 */
public class AddTextWithStyle extends Application {

    @Override
    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();
        pane.setTop(addHBox("Top"));
        pane.setLeft(addVBox("Left"));
        pane.setRight(addVBox("Right"));
        pane.setBottom(addHBox("Bottom"));

        Scene scene = new Scene(pane, 400, 450);
        scene.getStylesheets().add("text/textStyles.css");  // from Application root path

        primaryStage.setTitle("Adding Text!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static HBox addHBox(String str) {
        HBox hbox = new HBox();
        Text text = new Text(str);
        text.setId("textstyle");
        hbox.setId("hbox");
        hbox.getChildren().add(text);
        return hbox;
    }

    private static VBox addVBox(String str) {
        VBox vbox = new VBox();
        Text text = new Text(str);
        text.setId("textstyle");
        vbox.setId("vbox");
        vbox.getChildren().add(text);
        return vbox;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
