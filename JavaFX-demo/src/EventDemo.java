import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Event Capturing/Bubbling Demo
 */
public class EventDemo extends Application {

    @Override
    public void start(Stage stage) {
        Button b1 = new Button("First");
        b1.addEventFilter(MouseEvent.MOUSE_CLICKED,
                          event -> System.out.println("Button1 - EventFilter"));
        b1.addEventHandler(MouseEvent.MOUSE_CLICKED,
                           event -> System.out.println("Button1 - EventHandler"));

        Button b2 = new Button("Second");
        b2.addEventFilter(MouseEvent.MOUSE_CLICKED,
                          event -> {
                              System.out.println("Button2 - EventFilter");
                              event.consume();
                          });
        b2.addEventHandler(MouseEvent.MOUSE_CLICKED,
                           event -> System.out.println("Button2 - EventHandler"));

        Button b3 = new Button("Third");
        b3.setOnAction(event -> System.out.println("Button3 - ActionEvent"));

        FlowPane pane = new FlowPane();
        pane.getChildren().addAll(b1, b2, b3);
        pane.addEventFilter(MouseEvent.MOUSE_CLICKED,
                            event -> {
                                System.out.println("Pane - EventFilter");
                                //event.consume();
                            });
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED,
                             event -> System.out.println("Pane - EventHandler"));

        Scene scene = new Scene(pane, 300, 400);
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED,
                             event -> System.out.println("Scene - EventFilter"));
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED,
                              event -> System.out.println("Scene - EventHandler"));

        stage.setScene(scene);
        stage.setTitle("Event Propagation");
        stage.addEventFilter(MouseEvent.MOUSE_CLICKED,
                             event -> System.out.println("Stage - EventFilter"));
        stage.addEventHandler(MouseEvent.MOUSE_CLICKED,
                              event -> System.out.println("Stage - EventHandler"));
        stage.setOnCloseRequest(event -> System.out.println(event.getEventType().getName()));
        stage.show();
    }

    public static void main(String... args) {
        launch(args);
    }
}
