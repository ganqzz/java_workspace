package styles;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 */
public class StyleRules extends Application {

    Circle cir;
    Circle cir2;

    @Override
    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();

        Button btn = new Button();
        btn.setText("Click Me!");

        Button btn2 = new Button();
        btn2.setId("center");
        btn2.setText("Now Click Me!");

        btn.setOnAction(e -> btn.setVisible(false));
        btn2.setOnAction(e -> {
            cir2.setFill(Color.DEEPPINK);
            btn.setVisible(true);
        });

        cir = new Circle(100, 100, 40);
        cir2 = new Circle(100, 100, 60);
        cir.setId("center");
        cir2.setId("center");

        StackPane stack = new StackPane();
        StackPane stack2 = new StackPane();
        stack.getChildren().addAll(cir, btn);
        stack.setAlignment(Pos.CENTER);
        stack2.getChildren().addAll(cir2, btn2);
        stack2.setAlignment(Pos.CENTER);

        pane.setTop(stack);
        pane.setCenter(stack2);

        Scene scene = new Scene(pane, 400, 400);
        scene.getStylesheets().add("styles/style.css");

        primaryStage.setTitle("Style Rules");
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
