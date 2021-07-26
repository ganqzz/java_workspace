import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 */
public class GridAndBorderDemo extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(20);
        grid.setGridLinesVisible(true);
        Button b1 = new Button("First Button");
        Button b2 = new Button("Longer Second Button");
        Button b3 = new Button("Third");
        Button b4 = new Button("Fourth");

        GridPane.setConstraints(b1, 1, 1);
        GridPane.setConstraints(b2, 1, 2);
        GridPane.setConstraints(b3, 2, 1);
        GridPane.setConstraints(b4, 2, 2);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Button("Top"));
        borderPane.setBottom(new Button("Bottom"));
        borderPane.setLeft(new Button("Left"));
        borderPane.setRight(new Button("Right"));
        borderPane.setCenter(new Button("Center"));

        GridPane.setConstraints(borderPane, 1, 3, 3, 2);

        grid.getChildren().addAll(b1, b2, b3, b4, borderPane);

        Scene scene = new Scene(grid, 600, 400);

        stage.setScene(scene);
        stage.setTitle("Our Application!!!");
        //stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String... args) {
        launch(args);
    }
}
