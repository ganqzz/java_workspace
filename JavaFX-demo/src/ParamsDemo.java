import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.Group;

import java.util.Map;

public class ParamsDemo extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello JavaFx");

        System.out.println(getParameters().getRaw());
        System.out.println(getParameters().getNamed());

        Map<String, String> named = getParameters().getNamed();
        String widthStr = named.get("width"); // --width=X
        String heightStr = named.get("height"); // --height=Y

        double width = widthStr == null ? 200 : Double.parseDouble(widthStr);
        double height = heightStr == null ? 150 : Double.parseDouble(heightStr);

        Group root = new Group(new Label("Hello world"));

        Scene scene = new Scene(root, width, height);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
