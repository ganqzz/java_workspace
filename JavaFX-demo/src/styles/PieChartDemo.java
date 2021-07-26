package styles;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * PieChart & Style Demo
 */
public class PieChartDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        setUserAgentStylesheet(STYLESHEET_CASPIAN);

        BorderPane pane = new BorderPane();

        pane.setTop(hbox());
        pane.setCenter(pc());
        pane.setLeft(vbox());

        Scene scene = new Scene(pane, 700, 700);
        scene.getStylesheets().add("styles/piestyle.css");

        primaryStage.setTitle("Style Challenge");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static HBox hbox() {
        Button btn = new Button();
        btn.setText("Modena");
        btn.setPrefSize(100, 20);
        btn.setOnAction(event -> setUserAgentStylesheet(STYLESHEET_MODENA));

        Button btn2 = new Button();
        btn2.setText("Caspian");
        btn2.setPrefSize(100, 20);
        btn2.setOnAction(event -> setUserAgentStylesheet(STYLESHEET_CASPIAN));

        HBox hbox = new HBox();
        hbox.getStyleClass().add("hbox");
        hbox.getChildren().addAll(btn, btn2);

        return hbox;
    }

    private static VBox vbox() {
        Label lbl = new Label("Label1");
        Label lbl2 = new Label("Label2");
        VBox vbox = new VBox();
        vbox.getStyleClass().addAll("vbox");
        vbox.getChildren().addAll(lbl, lbl2);
        return vbox;
    }

    private static PieChart pc() {
        ObservableList<PieChart.Data> pieData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Rent", 500),
                        new PieChart.Data("Electric", 125),
                        new PieChart.Data("Groceries", 235),
                        new PieChart.Data("Entertainment", 200),
                        new PieChart.Data("Cell Phone", 80));

        PieChart budget = new PieChart(pieData);
        budget.setTitle("Monthly Expenses");
        return budget;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
