import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 */
public class TreeViewExample extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();

        TreeView<String> tree = new TreeView<>();
        Node rootIcon = new ImageView("img/folder_16.png");
        TreeItem<String> rootItem = new TreeItem<>("Inbox", rootIcon);
        rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<>("Message " + i);
            rootItem.getChildren().add(item);
        }
        tree.setRoot(rootItem);
        root.getChildren().add(tree);

        Scene scene = new Scene(root);

        stage.setTitle("Tree View Example");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
