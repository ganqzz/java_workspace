import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 */
public class LayoutExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setTop(createMenuBar(primaryStage));
        root.setLeft(addVBox());
        root.setCenter(addGridPane());
        root.setBottom(addFooter());

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("UI Layouts");
        primaryStage.setScene(scene);
        //primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private MenuBar createMenuBar(Stage stage) {
        // File menu - new, save, print, and exit
        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem printMenuItem = new MenuItem("Print");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        // add the new menuitems to the fileMenu
        fileMenu.getItems().addAll(newMenuItem, saveMenuItem, printMenuItem,
                                   new SeparatorMenuItem(), exitMenuItem);

        Menu languageMenu = new Menu("Language");
        CheckMenuItem javaMenuItem = new CheckMenuItem("Java");
        CheckMenuItem pythonMenuItem = new CheckMenuItem("Python");
        CheckMenuItem htmlMenuItem = new CheckMenuItem("HTML");
        languageMenu.getItems().addAll(javaMenuItem, pythonMenuItem, htmlMenuItem);

        CheckMenuItem FXMenuItem = new CheckMenuItem("JavaFX");
        FXMenuItem.setSelected(true);
        languageMenu.getItems().addAll(new SeparatorMenuItem(), FXMenuItem);

        Menu tutorialMenu = new Menu("Tutorial");
        tutorialMenu.getItems().addAll(
                new MenuItem("Buttons"),
                new MenuItem("Menus"),
                new MenuItem("Images"));
        languageMenu.getItems().add(tutorialMenu);

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        menuBar.getMenus().addAll(fileMenu, languageMenu);

        return menuBar;
    }

    private HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button buttonCurrent = new Button("Home");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("Exit");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);

        return hbox;
    }

    private HBox addFooter() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(4, 8, 4, 8));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699");

        Text copyright = new Text("Copyright 2016");
        copyright.setFill(Color.WHITE);
        copyright.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        hbox.getChildren().add(copyright);

        return hbox;
    }

    private VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Departments");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        Hyperlink options[] = new Hyperlink[]{
                new Hyperlink("Operations"),
                new Hyperlink("Customer Service"),
                new Hyperlink("Marketing"),
                new Hyperlink("Sales")};

        for (int i = 0; i < 4; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);
        }

        return vbox;
    }

    private GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        // Category in column 2, row 1
        Text category = new Text("Operations:");
        category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(category, 1, 0);

        // Title in column 3, row 1
        Text chartTitle = new Text("Customer Service");
        chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(chartTitle, 2, 0);

        ImageView imageHouse = new ImageView("img/home.gif");
        grid.add(imageHouse, 0, 0, 1, 2);

        return grid;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
