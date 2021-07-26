package simple;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SimpleUIController {

    @FXML
    public TextField textfield;

    // Initializableでなくても呼ばれる
    public void initialize() {
        textfield.setText("Text on Controller initialize");
    }

    @FXML
    public void onAction(ActionEvent actionEvent) {
        System.out.println("Action!");
    }
}
