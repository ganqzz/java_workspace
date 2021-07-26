import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * BarChart Demo
 */
public class QuarterlySales extends Application {

    @Override
    public void start(Stage primaryStage) {

        setUserAgentStylesheet(STYLESHEET_CASPIAN);

        CategoryAxis hAxis = new CategoryAxis();
        hAxis.setLabel("SalesPerson");

        NumberAxis vAxis = new NumberAxis();
        vAxis.setLabel("New Car Sales");

        BarChart<String, Number> bcSales = new BarChart<>(hAxis, vAxis);
        bcSales.setTitle("1st Quarter Sales Report");

        XYChart.Series<String, Number> jan = new XYChart.Series<>();
        XYChart.Series<String, Number> feb = new XYChart.Series<>();
        XYChart.Series<String, Number> mar = new XYChart.Series<>();

        jan.setName("January");
        feb.setName("February");
        mar.setName("March");

        jan.getData().add(new XYChart.Data<>("Mary", 120000));
        jan.getData().add(new XYChart.Data<>("Tom", 100000));

        feb.getData().add(new XYChart.Data<>("Mary", 90000));
        feb.getData().add(new XYChart.Data<>("Tom", 50000));

        mar.getData().add(new XYChart.Data<>("Mary", 55000));
        mar.getData().add(new XYChart.Data<>("Tom", 130000));

        bcSales.getData().add(jan);
        bcSales.getData().add(feb);
        bcSales.getData().add(mar);

        Button btn = new Button();
        btn.setText("Toggle Style");
        btn.setPrefSize(100, 20);
        btn.setOnAction(event -> {
            if (getUserAgentStylesheet().equals(STYLESHEET_CASPIAN)) {
                setUserAgentStylesheet(STYLESHEET_MODENA);
            } else {
                setUserAgentStylesheet(STYLESHEET_CASPIAN);
            }
        });

        FlowPane root = new FlowPane(Orientation.VERTICAL, btn, bcSales);
        root.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(root, 600, 500);

        primaryStage.setTitle("Quarterly Sales");
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
